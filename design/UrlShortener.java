/**
 * To avoid long URLs in SMS (Short Message Service), twitter, QR (Quick Response) code,
 * Twitter (t.co), Google (goo.gl), and others are offering services to convert a long URL
 * to short URLs.
 *
 * Basically the procedure of using a short URL is
 * 1. The browser sends request to the URL shortening service
 * 2. The service redirect the request to the actual web link
 *
 * The way of the URL shortening service works is that
 * 1. The service maintains a table to store the long URLs. The ID of the row will be used
 *    to generate the short URL
 * 2. The service needs to make sure the same URL will NOT saved twice, so that it would have
 *    more than one IDs, thus leading to more than one short URL
 * 3. When a URL shortening request comes, it is saved to the table if needed, and then its ID
 *    is being used to generate the URL
 * 4. When a short URL comes, it is reverted back to the row ID so that the long URL could be
 *    fetched.
 */

public class UrlShortener {
    private char[] validChars;

    public UrlShortener() {
        validChars = new char[36];
        int pos = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            validChars[pos++] = c;
        }
        for (char c = '0'; c <= '9'; c++) {
            validChars[pos++] = c;
        }
    }

    public String idToShortUrl(int id) {
        // Assume in the short URL, a-z and 0-9 are to be used. Therefore, we have 36 characters.
        // Now the problem becomes converting base-10 id to base-36 short URL.
        int base = validChars.length;
        StringBuilder sb = new StringBuilder();
        while (id != 0) {
            int remainder = id % base;
            id /= base;
            sb.insert(0, validChars[remainder]);
        }

        return sb.toString();
    }

    public int shortUrlToId(String url) {
        // Note that the value at this position is 36-based.
        int base = validChars.length;
        int id = 0;
        for (int i = 0; i < url.length(); i++) {
            char ch = url.charAt(i);
            int val;
            if (ch >= 'a' && ch <= 'z') {
                val = ch - 'a';
            } else {
                val = 26 + ch - '0';
            }

            id = id * base + val;
        }

        return id;
    }

    public static void main(String[] args) {
        UrlShortener sol = new UrlShortener();

        String shortUrl = sol.idToShortUrl(1000);
        System.out.println("Short URL is " + shortUrl);

        int id = sol.shortUrlToId(shortUrl);
        System.out.println("id is " + id);
    }
}
