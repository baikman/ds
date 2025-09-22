package numeric;

/**
 * Exception thrown when a negative value is provided for factorial computation.
 */
public class NegativeFactorial extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public NegativeFactorial() {
        super();
    }

    public NegativeFactorial(String msg) {
        super(msg);
    }
}
