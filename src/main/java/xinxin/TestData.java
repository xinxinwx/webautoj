package xinxin;

public class TestData {
    private String tel;
    private String pwd;
    private String expect;
    private String expectelement;

    public TestData() {
    }

    public TestData(String tel, String pwd, String expect, String expectelement) {
        this.tel = tel;
        this.pwd = pwd;
        this.expect = expect;
        this.expectelement = expectelement;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "tel='" + tel + '\'' +
                ", pwd='" + pwd + '\'' +
                ", expect='" + expect + '\'' +
                ", expectelement='" + expectelement + '\'' +
                '}';
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getExpectelement() {
        return expectelement;
    }

    public void setExpectelement(String expectelement) {
        this.expectelement = expectelement;
    }
}
