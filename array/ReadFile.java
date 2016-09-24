/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters
 * left in the file.
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * Note:
 * The read function will only be called once for each test case.
 */

public class ReadFile {
    private int read4(char[] buf) {
        return 4;
    }

    public int read(char[] buf, int n) {
        char[] tmpBuf = new char[4];
        int count = 0;
        boolean eof = false;
        while (count < n && !eof) {
            int nRead = read4(tmpBuf);

            if (nRead < 4) {
                eof = true;
            }

            nRead = Math.min(nRead, n - count);
            System.arraycopy(tmpBuf, 0, buf, count, nRead);
            count += nRead;
        }

        return count;
    }
}
