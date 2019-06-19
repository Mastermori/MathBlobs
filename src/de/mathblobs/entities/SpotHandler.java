package de.mathblobs.entities;

import java.util.LinkedList;
import java.util.List;

public class SpotHandler {

    private List<StandingSpot> spots;
    private List<StandingSpot> add;
    private List<StandingSpot> rem;

    public SpotHandler() {
        spots = new LinkedList<>();
        add = new LinkedList<>();
        rem = new LinkedList<>();
    }

    public StandingSpot getRightSpot(StandingSpot spot) {
        sort();
        int newSpot = spots.indexOf(spot);
        if (newSpot - 1 >= 0)
            return spots.get(newSpot - 1);
        else
            return spot;
    }

    public StandingSpot getLeftSpot(StandingSpot spot) {
        sort();
        int newSpot = spots.indexOf(spot);
        if (newSpot + 1 < spots.size())
            return spots.get(newSpot + 1);
        else
            return spot;
    }

    private void sort(){
        spots.sort((o1, o2) -> Float.compare(o2.getPos().x, o1.getPos().x));
        /*for (StandingSpot s : spots) {
            System.out.println(s.toString());
        }*/
    }

    public StandingSpot get(int i) {
        return spots.get(i);
    }

    public void draw() {
        spots.forEach(StandingSpot::draw);
    }

    //---------- LIST MANAGEMENT METHODS ----------

    /**
     * Adds and removes spots from their respective lists and clears the add/rem lists
     */
    public void updateLists() {
        if (!rem.isEmpty()) {
            spots.removeAll(rem);
            rem.clear();
        }
        if (!add.isEmpty()) {
            spots.addAll(add);
            add.clear();
        }
    }

    /**
     * Adds a spot to the handler (added next update call)
     *
     * @param spot
     */
    public void addSpot(StandingSpot spot) {
        add.add(spot);
    }

    /**
     * Removes a spot from the handler (removed next update call)
     */
    public void removeSpot(StandingSpot spot) {
        rem.add(spot);
    }

}
