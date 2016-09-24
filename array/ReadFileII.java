/**
 * /**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters
 * left in the file.
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * Note:
 * The read function may be called multiple times.
 */

public class ReadFileII {
    private char[] leftover  = new char[4];
    private int leftoverSize = 0;

    public int read(char[] buf, int n) {
        int count = 0;
        if (leftoverSize > 0) {
            count = Math.min(leftoverSize, n);
            System.arraycopy(leftover, 0, buf, 0, count);

            leftoverSize -= count;
            for (int i = 0; i < leftoverSize; i++) {
                leftover[i] = leftover[i + count];
            }
        }

        boolean eof = false;
        char[] tmpBuf = new char[4];
        while (count < n && !eof) {
            int nRead = read4(tmpBuf);
            if (nRead < 4) {
                eof = true;
            }

            int toCopy = Math.min(nRead, n - count);
            System.arraycopy(tmpBuf, 0, buf, count, toCopy);

            if (toCopy < nRead) {
                System.arraycopy(tmpBuf, toCopy, leftover, 0, nRead - toCopy);
            }

            count += toCopy;
        }

        return count;
    }

    private int read4(char[] buf) {
        return 4;
    }

}
