package ren.com.cn.common.enums;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/11/26 14:24
 * @email: renhongqiang1397@gmail.com
 */
public enum Planet {
    MERCURY(3.302e+23, 2.439e6),
    NENUS(3.302e+23, 6.052e6),;

    private final double mass;
    private final double radius;
    private final double surfaceGravity;

    private static final double G = 6.67300E-11;

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G * mass;
    }

    public double mass() {
        return mass;
    }

    public double radius() {
        return radius;
    }

    public double sufaceGravity() {
        return surfaceGravity;
    }

    public double surfaceWeight() {
        return mass * surfaceGravity;
    }

    public static void main(String[] args) {
        System.out.println(Planet.NENUS.mass);



    }

}
