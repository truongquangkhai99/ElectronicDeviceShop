package castudy.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validations {

    public static boolean validateName(String name) {
        return (name != null&&name.length()<=255);
    }

    public static boolean validatePrice(String number) {
        if (number == null) {
            return false;
        }
        try {
            Float strNum = Float.parseFloat(number);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean validateLinkImage(String linkImage) {
        return (linkImage != null&&linkImage.length()<=500);
    }

    public static boolean validateColor(String color) {
        return (color.length()<=45);
    }

    public static boolean validateInt(String number) {
        if (number == null) {
            return false;
        }
        try {
            int strNum = Integer.parseInt(number);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean validateMoTa(String moTa) {
        return (moTa.length()<=500);
    }

    public static boolean validateDate(String date) {
        if (date==null){
            return false;
        }
        if(date.trim().equals("")){
            return  true;
        }else {
            SimpleDateFormat vlDate=new SimpleDateFormat("yyyy-mm-dd");
            vlDate.setLenient(false);
            try {
                Date javaDate = vlDate.parse(date);
            }catch (ParseException e){
                return false;
            }
        }
        return true;
    }

    public static boolean validateAddress(String address) {
        return (address.length()<=500&&address!=null);
    }


    public static boolean validateEmail(String email){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex)&&email.length()<=50;
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        String regex = "\\d{10,11}";
        return phoneNumber.matches(regex);
    }

    public static boolean validatePass(String pass) {
        return pass.length()>=6&&pass.length()<=20;
    }

    public static boolean validateUserName(String name) {
        return name.length()>=6&&name.length()<=20;
    }

    public boolean validateProduct(String name,String price,String image,String color,String amount,String ngaySanXuat,String hanSudung,String mota){
        if (validateName(name)&&validateDate(ngaySanXuat)&&validateDate(hanSudung)&&validatePrice(price)&&validateLinkImage(image)&&validateColor(color)&&validateInt(amount)&&validateMoTa(mota)){
            return true;
        }else return false;
    }

    public  boolean validateProductLine(String name,String moTa){
        if (validateMoTa(moTa)&&validateName(name)){
            return true;
        }else return false;
    }

    public  boolean validateProductVendor(String name,String address,String email,String phone,String image){
        if (validateName(name)&&validateAddress(address)&&validateEmail(email)&&validatePhoneNumber(phone)&&validateLinkImage(image)){
            return true;
        }else return false;
    }

    public  boolean validateUser(String name,String pass,String email){
        if (validateUserName(name)&&validateEmail(email)&&validatePass(pass)){
            return true;
        }else return false;
    }

    public boolean validateBlog(String title,String link,String image){
        if (validateName(title)&&validateLinkImage(link)&&validateLinkImage(image)){
            return true;
        }else return false;
    }

}
