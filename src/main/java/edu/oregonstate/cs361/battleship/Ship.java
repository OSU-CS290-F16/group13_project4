package edu.oregonstate.cs361.battleship;

import java.util.List;

/**
 * Created by michaelhilton on 1/5/17.
 */
public class Ship {
    protected String name;
    private int length;
    private Coordinate start;
    private Coordinate end;

    public Ship(String n, int l,Coordinate s, Coordinate e) {
        name = n;
        length = l;
        start = s;
        end = e;
    }

    public Ship(String n, int l, ShipLocation s) {
        this(
                n,
                l,
                s.getStart(),
                new Coordinate(
                        s.getIsHorizontal() ? s.getStart().getAcross() : s.getStart().getAcross() + l,
                        s.getIsHorizontal() ? s.getStart().getDown() + l : s.getStart().getDown()
                )
        );
    }


    public void setLocation(Coordinate s, Coordinate e) {
        start = s;
        end = e;
    }

    public boolean covers(Coordinate test) {
        //horizontal
        if(start.getAcross() == end.getAcross()){
            if(test.getAcross() == start.getAcross()){
                if((test.getDown() >= start.getDown()) &&
                (test.getDown() <= end.getDown()))
                return true;
            } else {
                return false;
            }
        }
        //vertical
        else{
            if(test.getDown() == start.getDown()){
                if((test.getAcross() >= start.getAcross()) &&
                        (test.getAcross() <= end.getAcross()))
                    return true;
            } else {
                return false;
            }

        }
        return false;
    }

    public String getName() {
        return name;
    }

    /*
     * Default implementation of scan.
     */
    public  boolean scan(Coordinate coor) {
        if(covers(coor)){
            return true;
        }
        if(covers(new Coordinate(coor.getAcross()-1,coor.getDown()))){
            return true;
        }
        if(covers(new Coordinate(coor.getAcross()+1,coor.getDown()))){
            return true;
        }
        if(covers(new Coordinate(coor.getAcross(),coor.getDown()-1))){
            return true;
        }
        if(covers(new Coordinate(coor.getAcross(),coor.getDown()+1))){
            return true;
        }
        return false;
    }

    /*
     *  Default implementation of isSunk. returns true if all spaces are covered.
     */
    public boolean isSunk(List<Coordinate> hits) {
        int totalShipHits = 0;
        for(Coordinate c : hits) {
            if(covers(c)) totalShipHits++;
        }
        boolean ret = totalShipHits == length;
        System.out.println(name + " is " + (ret ? "" : "not") + " sunk");
        return ret;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Ship { start: [" + start + "] end: [" + end + "]}";
    }
}
