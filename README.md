# Java Implementation of Recommendation ITU-R P.2108-1

This code repository contains a Java software implementation of Recommendation [ITU-R P.2108-1](https://www.itu.int/rec/R-REC-P.2108/en) with a prediction procedure for  estimating loss through clutter at frequencies between 30 MHz and 100 GHz.  

This version of the code is functionally identical to the reference version approved by ITU-R Working Parties 3K and 3M and published by Study Group 3 on [ITU-R SG 3 Software, Data, and Validation Web Page](https://www.itu.int/en/ITU-R/study-groups/rsg3/Pages/iono-tropo-spheric.aspx). This version of the code is also implemented in [SEAMCAT](https://seamcat.org).

The following table describes the structure of the folder `./src/` containing the Java implementation of Recommendation ITU-R P.2108.

| File/Folder               | Description                                                         |
|----------------------------|---------------------------------------------------------------------|
|`main/P2108.java`                | Java class implementing Recommendation ITU-R P.2108-1          |
|`test/P2108Test.java`          | Java class implementing validation tests against the reference Excel implementation of this Recommendation for a range of location percentages, frequencies, distances and environment categories.          |

## Functions

## 1.  Site-specific height gain terminal correction model §3.1
~~~
Lc = cl_loss1(f,  h,  eqnum,  R, ws);
~~~

| Variable          | Type   | Units | Limits       | Description  |
|-------------------|--------|-------|--------------|--------------|
| `f`               | scalar double | GHz   | 0.03 ≤ `f` ≤ 3 | Frequency   |
| `h`          | scalar double | m    |   | Antenna height |
| `eqnum`           | scalar int    |       | 1  or 2             |  Equation corresponding to representative clutter type:  <ul><li>water/sea: eqnum = 2,  R = 10 m </li><li>open/rural: eqnum = 2, R = 10 m</li><li>suburban: eqnum = 1, R = 10 m</li><li>urban/trees/forest: eqnum = 1, R = 15 m</li><li>dense urban: eqnum = 1, R = 20 m</li></ul>
| `R`           | scalar double    | m      |           |  Representative clutter height. It should be set to the values given above, unless there is specific local information available. |
| `ws`           | scalar double    | m      |          |  Relates to the width of the street. It should be set to 27 m unless there is specific local information available.|

## 2.  Statistical clutter loss model for terrestrial paths §3.2
~~~
Lc = cl_loss2( f, d, p);
~~~

| Variable          | Type   | Units | Limits       | Description  |
|-------------------|--------|-------|--------------|--------------|
| `f`               | scalar double | GHz   | 0.5 ≤ `f` ≤ 67 | Frequency   |
| `d`          | scalar double | km    |   | Distance <ul><li>0.25 < `d` < 1: correction to be applied at one end only</li> <li> 1 ≤ `d`: correction can be applied at both ends of the path</li></ul>|
| `p`           | scalar double    | %     |     0 < p < 100     |  Percentage of locations|


## 3.  Earth-space and Aeronautical statistical clutter loss model  §3.3
~~~
Lc = cl_loss3( f, th, p);
~~~
| Variable          | Type   | Units | Limits       | Description  |
|-------------------|--------|-------|--------------|--------------|
| `f`               | scalar double | GHz   | 10 ≤ `f` ≤ 100 | Frequency   |
| `th`          | scalar double | deg   | 0 ≤ `th` ≤ 90  | Elevation angle|
| `p`           | scalar double    | %     |     0 < p < 100     |  Percentage of locations|

 ## Outputs ##

| Variable   | Type   | Units | Description |
|------------|--------|-------|-------------|
| `Lc`    | double | dB    | Clutter loss |

## References

* [Recommendation ITU-R P.2108](https://www.itu.int/rec/R-REC-P.2108/en)

* [ITU-R SG 3 Software, Data, and Validation Web Page](https://www.itu.int/en/ITU-R/study-groups/rsg3/Pages/iono-tropo-spheric.aspx)

* [SEAMCAT - Spectrum Engineering Advanced Monte Carlo Analysis Tool](https://seamcat.org)
