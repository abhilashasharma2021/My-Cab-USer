package com.mycabuser.retrofitmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverDetailResponce {

    @SerializedName("user_data")
    @Expose
    private UserData userData;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("result")
    @Expose
    private String result;

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    public class UserData {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("vehicle_id")
        @Expose
        private String vehicleId;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("aouth_id")
        @Expose
        private String aouthId;
        @SerializedName("aouth_provider")
        @Expose
        private String aouthProvider;
        @SerializedName("regid")
        @Expose
        private String regid;
        @SerializedName("DOB")
        @Expose
        private String dob;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("phone_number")
        @Expose
        private String phoneNumber;
        @SerializedName("otp")
        @Expose
        private String otp;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("promo_code_status")
        @Expose
        private String promoCodeStatus;
        @SerializedName("login_status")
        @Expose
        private String loginStatus;
        @SerializedName("category_id")
        @Expose
        private String categoryId;
        @SerializedName("otp_varify_status")
        @Expose
        private String otpVarifyStatus;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAouthId() {
            return aouthId;
        }

        public void setAouthId(String aouthId) {
            this.aouthId = aouthId;
        }

        public String getAouthProvider() {
            return aouthProvider;
        }

        public void setAouthProvider(String aouthProvider) {
            this.aouthProvider = aouthProvider;
        }

        public String getRegid() {
            return regid;
        }

        public void setRegid(String regid) {
            this.regid = regid;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getPromoCodeStatus() {
            return promoCodeStatus;
        }

        public void setPromoCodeStatus(String promoCodeStatus) {
            this.promoCodeStatus = promoCodeStatus;
        }

        public String getLoginStatus() {
            return loginStatus;
        }

        public void setLoginStatus(String loginStatus) {
            this.loginStatus = loginStatus;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getOtpVarifyStatus() {
            return otpVarifyStatus;
        }

        public void setOtpVarifyStatus(String otpVarifyStatus) {
            this.otpVarifyStatus = otpVarifyStatus;
        }

    }
}


