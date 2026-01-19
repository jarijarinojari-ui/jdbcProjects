public class Javabean {
    private String num = null;
    private String name = null;
    private String sex = null;
    private String aff = null;
    private String phone = null;

    public Javabean() {

    }
    public Javabean(String name,String sex, String aff, String phone) {
        this.name = name;
        this.sex = sex;
        this.aff = aff;
        this.phone = phone;
    }


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAff() {
        return aff;
    }

    public void setAff(String aff) {
        this.aff = aff;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
