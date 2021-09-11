package net.GenSpark.navy;


interface INavyStamp {
    void placeShips();
    void fireAt(INavyStamp opponent);
    int getStillAlive();
}