/*
       Modify the previous HeavenlyBody example so that the HeavenlyBody
       class also has a "bodyType" field. This field will store the
       type of HeavenlyBody (such as STAR, PLANET, MOON, etc).

       You can include as many types as you want, but must support at
       least PLANET and MOON.

       For each of the types that you support, subclass the HeavenlyBody class
       so that your program can create objects of the appropriate type.

       Although astronomers may shudder at this, our solar systems will
       allow two bodies to have the same name as long as they are not the
       same type of body: so you could have a star called "BetaMinor" and
       an asteroid also called "BetaMinor", for example.

       Hint: This is much easier to implement for the Set than it is for the Map,
       because the Map will need a key that uses both fields.

       There is a restriction that the only satellites that planets can have must
       be moons. Even if you don't implement a STAR type, though, your program
       should not prevent one being added in the future (and a STAR's satellites
       can be almost every kind of HeavenlyBody).

       Test cases:
       1. The planets and moons that we added in the previous video should appear in
       the solarSystem collection and in the sets of moons for the appropriate planets.

       2. a.equals(b) must return the same result as b.equals(a) - equals is symmetric.

       3. Attempting to add a duplicate to a Set must result in no change to the set (so
       the original value is not replaced by the new one).

       4. Attempting to add a duplicate to a Map results in the original being replaced
       by the new object.

       5. Two bodies with the same name but different designations can be added to the same set.

       6. Two bodies with the same name but different designations can be added to the same map,
       and can be retrieved from the map.
*/

package com.timbuchalka;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dev on 12/01/2016.
 */
public class HeavenlyBody implements HeavenlyBodyInt {
    private final String bodyType;
    private final String name;
    private final double orbitalPeriod;
    private final Set<Moon> satellites;

    public HeavenlyBody(String bodyType, String name, double orbitalPeriod) {
        this.bodyType = bodyType;
        this.name = name;
        this.orbitalPeriod = orbitalPeriod;
        this.satellites = new HashSet<>();
    }

    public String getBodyType() {
        return bodyType;
    }

    public String getName() {
        return name;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public boolean addMoon(Moon moon) {
        if (moon != null)
            return this.satellites.add(moon);
        else return false;
    }

    public Set<Moon> getSatellites() {
        return new HashSet<>(this.satellites);
    }

    @Override
    public boolean equals(HeavenlyBody obj) {
        if (this == obj) {
            return true;
        }

        System.out.println("obj.getClass() is " + obj.getClass());
        System.out.println("this.getClass() is " + this.getClass());
        return this.getClass() == obj.getClass() && this.getName().equals(obj.getName());
    }

    @Override
    public int hashCode() {
//        System.out.println("hashcode called");
        return this.name.hashCode() + 57;
    }
}
