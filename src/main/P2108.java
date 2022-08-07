package main;

//Recommendation ITU-R P.2108-1
public class P2108 {

    //
    // Class implementation of Recommendation ITU-R P.2108
    //
    //
    // Rev   Date        Author                          Description
    //-------------------------------------------------------------------------------
    // v1    04MAY17     Ivica Stevanovic, OFCOM         Initial implementation in Java
    // v2    15JUL21     Ivica Stevanovic, OFCOM         Aligned with ITU-R P.2108-1
    //
    //  Copyright (c) 2017 - , Ivica Stevanovic
    //  All rights reserved.
    //
    // Redistribution and use in source and binary forms, with or without
    // modification, are permitted provided that the following conditions are
    // met:
    //
    //     * Redistributions of source code must retain the above copyright
    //       notice, this list of conditions and the following disclaimer.
    //     * Redistributions in binary form must reproduce the above copyright
    //       notice, this list of conditions and the following disclaimer in
    //       the documentation and/or other materials provided with the distribution
    //
    //
    ////
    // THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
    // AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
    // IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
    // ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
    // LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
    // CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
    // SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
    // INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
    // CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
    // ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
    // POSSIBILITY OF SUCH DAMAGE.
    //
    // THE AUTHORS AND OFCOM (CH) DO NOT PROVIDE ANY SUPPORT FOR THIS SOFTWARE
    ////

    public double cl_loss1(double f, double h, int eqnum, double R, double ws) {
        //cl_loss1 clutter loss according to P.2108-1 §3.1
        //   L = cl_loss1(f, h, eqnum, R, ws)
        //
        //   This function computes the median clutter loss
        //   as defined in ITU-R P.2108 (Section 3.1) using height-gain terminal
        //   correction method
        //
        //     Input parameters:
        //     f       -   Frequency (GHz): 0.03 - 3
        //     h       -   Antenna height (m)
        //     eq      -   equation corresponding to default:
        //                 2 - water/sea,  R = 10 m, Equation (2b)
        //                 2 - open/rural, R = 10 m, Equation (2b)
        //                 1 - suburban,   R = 10 m, Equation (2a)
        //                 1 - urban/trees/forest, R = 15 m, Equation (2a)
        //                 1 - dense urban,R = 20 m, Equation (2a)
        //     R       -   clutter height (m)
        //     ws      -   street width (m) , overwrites the default value ws = 27 m
        //
        //
        //     Output parameters:
        //     Ah     -   clutter loss according to P.2108 §3.1
        //
        //     Example:
        //     Ah = cl_loss1(f, h, eqnum, R, ws)

        //     Rev   Date        Author                          Description
        //     -------------------------------------------------------------------------------
        //     v0    01MAY17     Ivica Stevanovic, OFCOM         Initial version

        double Ah = 0;

        if (h >= R) {
            Ah = 0;
            return Ah;
        }

        double Knu = 0.342*Math.sqrt(f); // (2g)
        double Kh2 = 21.8 + 6.2*Math.log10(f); //(2f)
        double hdif = R - h; //(2d)
        double theta_c = Math.atan(hdif/ws)*180/Math.PI; //(2e)
        double nu = Knu*Math.sqrt(hdif*theta_c); //(2c)

        if (eqnum == 2) {

            Ah = -Kh2 * Math.log10(h / R); // (2b)

        }else {
            double J = 0;
            if (nu > -0.78) {


                J = 6.9 + 20 * Math.log10(Math.sqrt(Math.pow((nu - 0.1), 2) + 1) + nu - 0.1);

            }

            Ah = J - 6.03;
        }

        //System.out.printf("L = %f\n", L);
        //System.out.printf("========================================\n");

        return Ah;

    }

    public double cl_loss2(double f, double d, double p) {

        //cl_loss2 clutter loss according to P.2108-1 §3.2
        //   L = cl_loss2(f, d, p)
        //
        //   This function computes the statistical distribution of clutter loss
        //   as defined in ITU-R P.2108-1 (Section 3.2) for terrestrial paths in
        //   urban and suburban environments.
        //
        //     Input parameters:
        //     f       -   Frequency (GHz): 0.5 <= f <= 67
        //     d       -   distance (km):  0.25 < d < 1 (correction to be applied at one end only)
        //                                        d >= 1 (correction can be applied at both ends of the path)
        //     p       -   percentage of locations (%): 0 < p < 100
        //
        //     Output parameters:
        //     Lctt     -   clutter loss according to P.CLUTTER §3.1
        //
        //     Example:
        //     Lctt = cl_loss2(f, d, p)

        //     Rev   Date        Author                          Description
        //     -------------------------------------------------------------------------------
        //     v0    01MAY17     Ivica Stevanovic, OFCOM         Initial version
        //     v1    15JUL21     Ivica Stevanovic, OFCOM         Aligned with ITU-R P.2108-1

        // Read the input arguments and check them

        if (p <= 0 || p >= 100) {
            throw new RuntimeException("Percentage of locations is outside of the valid domain (0, 100) %");
        }

        //double  Ll = 23.5 + 9.6*Math.log10(f);   //(4)
        double Ll = -2.0*Math.log10(Math.pow(10, -5.0*Math.log10(f)-12.5) + Math.pow(10, -16.5));   //(4a)
        double sigmal = 4;                                                                          //(4b)
        double Ls = 32.98 + 23.9*Math.log10(d) + 3*Math.log10(f);  //(5a)
        double sigmas = 6;                                         //(5b)

        double sigmacb = Math.sqrt( (Math.pow(sigmal, 2.0) * Math.pow(10, -0.2*Ll) + Math.pow(sigmas, 2.0) * Math.pow(10, -0.2*Ls) ) / (Math.pow(10, -0.2*Ll) + Math.pow(10, -0.2*Ls)) );      //(3b)

        double Lctt = -5*Math.log10(Math.pow(10,(-0.2*Ll)) + Math.pow(10,(-0.2*Ls))) - sigmacb * norminv(1- p/100, 0, 1);  //(3)

        double Ls2 = 32.98 + 23.9*Math.log10(2.0) + 3*Math.log10(f);
        double sigmacb2 = Math.sqrt( (Math.pow(sigmal, 2.0) * Math.pow(10, -0.2*Ll) + Math.pow(sigmas, 2.0) * Math.pow(10, -0.2*Ls2) ) / (Math.pow(10, -0.2*Ll) + Math.pow(10, -0.2*Ls2)) );
        double Lctt2 = -5*Math.log10(Math.pow(10,(-0.2*Ll)) + Math.pow(10,(-0.2*Ls2))) - sigmacb2 * norminv(1- p/100, 0, 1);

        Lctt = Math.min(Lctt, Lctt2);

        return Lctt;


    }


    public double cl_loss3(double f, double th, double p) {
        //cl_loss3 clutter loss according to P.2108-1 §3.3
        //   L = cl_loss3(f, th, p)
        //
        //   This function computes the statistical distribution of clutter loss
        //   as defined in ITU-R P.2108 (Section 3.3) for Earth to Space and Aeronautical
        //   paths
        //
        //     Input parameters:
        //     f       -   Frequency (GHz): 10 <= f <= 100
        //     th      -   elevation angle (degrees):  0 <= th <= 90
        //     p       -   percentage of locations (%): 0 < p < 100
        //
        //     Output parameters:
        //     Lces     -   clutter loss according to P.2108 §3.3
        //
        //     Example:
        //     Lces = cl_loss3(f, th, p)

        //     Rev   Date        Author                          Description
        //     -------------------------------------------------------------------------------
        //     v0    01MAY17     Ivica Stevanovic, OFCOM         Initial version

        //// Read the input arguments and check them

        // Checking passed parameter to the defined limits



        if (th < 0 || th > 90) {
            throw new RuntimeException("Elevation angle is outside of the valid domain [0, 90] degrees");
        }


        if (p <= 0 || p >= 100) {
            throw new RuntimeException("Percentage of locations is outside of the valid domain (0, 100)");
        }

        double K1 = 93 * Math.pow(f, (0.175));
        double A1 = 0.05;

        double L1 = -K1 * Math.log(1 - p / 100);
        double L2 = 1.0 / Math.tan(A1 * (1 - th / 90) + Math.PI * th / 180);

        //System.out.printf("f = %f\n", f);
        //System.out.printf("th = %f\n", th);
        //System.out.printf("p = %f\n", p);

        double Lces = Math.pow((L1 * L2), (0.5 * (90 - th) / 90)) - 1 - 0.6 * norminv(1-p / 100, 0, 1);   //(6);
        //System.out.printf("Lces = %f\n", Lces);


        return Lces;
    }

    private double norminv(double p, double mu, double sigma) {
        //   This function computes the inverse of the normal distribution with mean mu and standard deviation sigma
        //
        //     Input parameters:
        //     p       -   percentage of locations (0-1)
        //     mu      -   mean of the normal distribution (dB)
        //     sigma   -   standard deviation of the normal distribution (dB)
        //
        //     Output parameters:
        //     y       -   value for which

        //
        //
        //     Rev   Date        Author                          Description
        //     -------------------------------------------------------------------------------
        //     v0    03MAY17     Ivica Stevanovic, OFCOM         Initial version in Java


        double y;

        y = mu + sigma* Qi(1-p);

        return y;
    }

    private double Qi(double x) {
        //Anex 5, Sec. 16 An approximation to the inverse complementary cumulative normal distribution
        // function
        // Rev     Date    Author                      Description
        // -------------------------------------------------------------------------------
        // v1      1DEC16  Ivica Stevanovic, OFCOM     Initial version

        double out;

        if (x <= .5) {
            out = T(x) - C(x);          //(39 a)
        } else {
            out = -(T(1 - x) - C(1 - x)); //(39 b)
        }

        return out;

    }

    private double T(double y) {
        double outT = Math.sqrt(-2 * Math.log(y));     //(39 c)
        return outT;
    }

    private double C(double z) {
        double C0 = 2.515517;
        double C1 = 0.802853;
        double C2 = 0.010328;
        double D1 = 1.432788;
        double D2 = 0.189269;
        double D3 = 0.001308;
        double outC = (((C2 * T(z) + C1) * T(z)) + C0) / (((D3 * T(z) + D2) * T(z) + D1) * T(z) + 1);//(39d)
        return outC;
    }

}


