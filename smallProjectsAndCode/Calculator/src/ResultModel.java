public class ResultModel {

    private double result = 0;
    private int previousOperation;
    //previousOperation is :
    //    - 1 - addition
    //    - 2 - substraction
    //    - 3 - multiplication
    //    - 4 - division
    //    - 5 - initial state

    public ResultModel() {
        previousOperation = 5;
    }
    public int getPreviousOperation() {
        return previousOperation;
    }

    public void setPreviousOperation(int previousOperation) {
        this.previousOperation = previousOperation;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
