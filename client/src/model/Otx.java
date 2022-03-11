package model;

import java.io.Serializable;
import java.util.Objects;

public class Otx implements Serializable {

    private static final long serialVersionUID = -2565570290688784024L;

    private String name;
    private String nameOtx;
    private Integer id;
    private Integer id_user;
    private String typeOtx;
    private String classOtx;
    private  Integer countOtx;

    public String getNameOtx() {
        return nameOtx;
    }

    public void setNameOtx(String nameOtx) {
        this.nameOtx = nameOtx;
    }

    public Otx(String name, Integer id, String typeOtx, String classOtx, Integer countOtx) {
        this.name = name;
        this.id = id;
        this.typeOtx = typeOtx;
        this.classOtx = classOtx;
        this.countOtx = countOtx;
    }

    public Otx() {
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeOtx() {
        return typeOtx;
    }

    public void setTypeOtx(String typeOtx) {
        this.typeOtx = typeOtx;
    }

    public String getClassOtx() {
        return classOtx;
    }

    public void setClassOtx(String classOtx) {
        this.classOtx = classOtx;
    }

    public Integer getCountOtx() {
        return countOtx;
    }

    public void setCountOtx(Integer countOtx) {
        this.countOtx = countOtx;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Otx)) return false;
        Otx otx = (Otx) o;
        return name.equals(otx.name) &&
                nameOtx.equals(otx.nameOtx) &&
                typeOtx.equals(otx.typeOtx) &&
                classOtx.equals(otx.classOtx) &&
                countOtx.equals(otx.countOtx) &&
                id.equals(otx.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nameOtx, id, id_user, typeOtx, classOtx, countOtx);
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", nameOtx='" + nameOtx + '\'' +
                ", id=" + id +
                ", id_user=" + id_user +
                ", typeOtx='" + typeOtx + '\'' +
                ", classOtx='" + classOtx + '\'' +
                ", countOtx=" + countOtx +
                '}';
    }
}
