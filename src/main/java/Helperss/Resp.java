package Helperss;

public class Resp {
    private Boolean error;
    private Result result;
    private String errMsg;
    //private int errCode;

    public Resp(Boolean error, Result result) {
        this.error = error;
        this.result = result;
    }

    public String getErrMsg() {
        return errMsg;
    }

    //public int getErrCode() {
   //    return errCode;
   //}

   //public void setErrCode(int errCode) {
   //    this.errCode = errCode;
   //}

    public Boolean getError() {
        return error;
    }



    public Result getResult() {
        return result;
    }


}
