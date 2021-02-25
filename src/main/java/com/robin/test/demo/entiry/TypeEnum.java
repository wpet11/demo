package com.robin.test.demo.entiry;

import lombok.Getter;

public enum  TypeEnum {

    ONE(1),

    TWO(2),

    THREE(3);

    @Getter
    private Integer level;

    TypeEnum(Integer level){
        this.level = level;
    }
    public static TypeEnum getByLevel(int level){
        TypeEnum[] typeEnums = TypeEnum.values();
        for (TypeEnum typeEnum : typeEnums) {
            if (typeEnum.getLevel() == level){
                return typeEnum;
            }
        }
        return null;
    }

}
