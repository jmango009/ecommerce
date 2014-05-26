/**
 * 
 */
package com.ecommerce.core.exception;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author richard
 * 
 */
public class ECRuntimeException extends RuntimeException implements Externalizable {

	private Throwable linkedException;
	private int errorCode;
	private String message;
	private Boolean isLinkedException = Boolean.FALSE;

	public ECRuntimeException() {
		this(null, 0, null);
	}

	public ECRuntimeException(String cause) {
		this(cause, 0, null);
	}

	public ECRuntimeException(Throwable t) {
		this("", 0, t);
	}

	public ECRuntimeException(String message, int errorCode) {
		this(message, errorCode, null);
	}

	public ECRuntimeException(String message, Throwable t) {
		this(message, 0, t);
	}

	public ECRuntimeException(String message, int errorCode, Throwable t) {
		this.message = message;
		this.errorCode = errorCode;
		this.linkedException = t;
		this.isLinkedException = linkedException != null ? Boolean.TRUE : Boolean.FALSE;
	}

	public Throwable getCause() {
		return this.linkedException;
	}

	public int getErrorCode() {
		return this.errorCode;
	}

	public String getMessage() { 
		return this.message; 
	}

	public String toString() {
		StringBuffer s = new StringBuffer();

		s.append(getClass().getName()).append(":");
		s.append("code: ").append(errorCode).append("; ");
		s.append("reason: ").append(message).append("\n");

		return s.toString();
	}

	public void printStackTrace() {
		synchronized (System.err) {
			_printStackTrace(System.err);
		}
	}

	public void printStackTrace(PrintStream s) {
		synchronized (s) {
			_printStackTrace(s);
		}
	}

	public void printStackTrace(PrintWriter s) {
		synchronized (s) {
			_printStackTrace(s);
		}
	}

	private void _printStackTrace(Object output) {
		if (linkedException != null) {
			if (output instanceof PrintStream) {
				((PrintStream) output).println(this);
				linkedException.printStackTrace((PrintStream) output);
			} else if (output instanceof PrintWriter) {
				((PrintWriter) output).println(this);
				linkedException.printStackTrace((PrintWriter) output);
			}
		} else if (!isLinkedException.booleanValue()) {
			if (output instanceof PrintStream) {
				super.printStackTrace((PrintStream) output);
			} else if (output instanceof PrintWriter) {
				super.printStackTrace((PrintWriter) output);
			}
		} else {
			if (output instanceof PrintStream) {
				((PrintStream) output).println(this);
			} else if (output instanceof PrintWriter) {
				((PrintWriter) output).println(this);
			}
		}
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeBoolean(isLinkedException.booleanValue());
		out.writeInt(errorCode);

		if (isLinkedException.booleanValue())
			out.writeObject(message + "\n\n"
					+ ECException.getStackTrace(linkedException));
		else
			out.writeObject(message + "\n" + ECException.getStackTrace(this));
	}

	/**
	 * The readExternal method must read the values in the same sequence and with
	 * the same types as were written by writeExternal
	 * 
     * @param in    the stream to read data from in order to restore the object
     * 
	 */
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		isLinkedException = (in.readBoolean()) ? Boolean.TRUE : Boolean.FALSE;
		errorCode = in.readInt();
		message = (String) in.readObject();
	}
	
	//==========================================================================
    // test driver
    //==========================================================================
    private final static void test1() {
        throw new ECRuntimeException("exception in test1()");
    }

    private final static void test2() {
        try {
            test1();
        } catch (Exception e) {
            throw new ECRuntimeException("exception in test2()", e);
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            test2();
        } catch (Exception e) {
            throw new ECRuntimeException("exception in main()", e);
        }
    }

}
