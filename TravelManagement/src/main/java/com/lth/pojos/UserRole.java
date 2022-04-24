package com.lth.pojos;

public enum UserRole {
    ADMIN("Người quản trị"), EMPLOYEE("Nhân viên"), CUSTOMER("Khách hàng");

    public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name) {
        return Enum.valueOf(enumType, name);
    }

    private String content;

    @Override
    public String toString() {
        return this.getContent();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    UserRole(String content) {
        this.content = content;
    }
}
