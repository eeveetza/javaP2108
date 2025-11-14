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
    // v3    14NOV25     Ivica Stevanovic, OFCOM         Aligned with ITU-R P.2108-2
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


    public double cl_loss3(double f, double theta, double p, double h, double hm) {
        //cl_loss3 clutter loss according to P.2108-2 §3.3
        //   L = cl_loss3(f, th, p, h ,hm)
        //
        //   This function computes the statistical distribution of clutter loss
        //   as defined in ITU-R P.2108 (Section 3.3) for Earth to Space and Aeronautical
        //   paths
        //
        //     Input parameters:
        //     f       -   Frequency (GHz): 10 <= f <= 100
        //     th      -   elevation angle (degrees):  0 <= th <= 90
        //     p       -   percentage of locations (%): 0 < p < 100
        //     h       -   ground station height (m): h >= 1
        //     hm      -   median clutter height (m): Low-rise: hm <= 8
        //                                            Mid-rise: 8 < hm <= 20
        //                                            High-rise: hm > 20
        //
        //     Output parameters:
        //     Lces     -   clutter loss according to P.2108 §3.3
        //
        //     Example:
        //     Lces = cl_loss3(f, theta, p, h, hm)

        //     Rev   Date        Author                          Description
        //     -------------------------------------------------------------------------------
        //     v0    12JUN25     Ivica Stevanovic, OFCOM         Initial version

        double ak, bk, ck, aC, bC, aV, bV;

        //// Read the input arguments and check them

        // Checking passed parameter to the defined limits

        if (f < 0.5 || f > 100) {
            throw new RuntimeException("Frequency is outside the valid domain [0.5, 100] GHz");
        }

        if (theta < 0 || theta > 90) {
            throw new RuntimeException("Elevation angle is outside of the valid domain [0, 90] degrees");
        }


        if (p <= 0 || p >= 100) {
            throw new RuntimeException("Percentage of locations is outside of the valid domain (0, 100)");
        }

        // Table 7: plos parameters for equations (7) and (8)
        if (hm <= 8) {
            // Low-rise
            ak = 4.9;
            bk = 6.7;
            ck = 2.6;
            aC = 0.19;
            bC = 0;
            aV = 1.4;
            bV = 74;

        }
        else if (hm > 8 && hm <= 20) {
            // Mid-rise
            ak = -2.6;
            bk = 6.6;
            ck = 2.0;
            aC = 0.42;
            bC = -6.7;
            aV = 0.15;
            bV = 97;

        } else {// hm > 20
            // High-rise
            ak = 2.4;
            bk = 7.0;
            ck = 1.0;
            aC = 0.19;
            bC = -2.7;
            aV = 0.15;
            bV = 98;
        }

        double akp, bkp, aCp, bC1p, bC2p, bC3p, bC4p, cCp, aVp, bVp;

        // Table 8: plos parameters for equations (9) and (10)
        if (hm <= 8) {
            // Low-rise
            akp = 6.0;
            bkp = 0.07;
            aCp = 0.15;
            bC1p = 5.4;
            bC2p = -0.3;
            bC3p = 3.2;
            bC4p = 0.07;
            cCp = -27;
            aVp = 1.6;
            bVp = -17;

        }  else if (hm > 8 && hm <= 20) {
            // Mid-rise
            akp = 3.6;
            bkp = 0.05;
            aCp = 0.17;
            bC1p = 13;
            bC2p = -0.2;
            bC3p = 3.7;
            bC4p = 0.05;
            cCp = -41;
            aVp = 1;
            bVp = -21;

        } else { // hm > 20
            // High-rise
            akp = 5.0;
            bkp = 0.003;
            aCp = 0.17;
            bC1p = 32.6;
            bC2p = 0.012;
            bC3p = -23.9;
            bC4p = -0.07;
            cCp = -41;
            aVp = 1;
            bVp = -18;
        }

        // LoS probability (7-8)

        double Vmax = Math.min(aV*h + bV, 100);
        double Ce = aC*h + bC;
        double k = Math.pow( (h + ak)/bk, ck);
        double pLoS = Math.max(0, Vmax * ( ( 1 - Math.exp( -k*(theta + Ce)/90.0 ) ) / (1 - Math.exp(-k*(90 + Ce) / 90.0 ) ) ) );

        // Conditional probability of Fresnel zone clearance (9-10)

        double Vmaxp = Math.min(aVp*h + bVp, 0) * Math.pow(f, (-0.55)) + 100;
        double Cep = Math.pow((f*1e9), aCp) + bC1p * Math.exp(bC2p * h) + bC3p * Math.exp(bC4p * h) + cCp;
        double kp = akp * Math.exp (bkp * h);
        double pFcLoS_LoS = Math.max(0, Vmaxp * ( ( 1 -Math. exp( -kp*(theta + Cep)/90.0 ) ) / (1 - Math.exp(-kp*(90 + Cep) / 90.0 ) ) ) );

// Probability of a link being Fresnel clear

                double pFcLoS = pLoS * pFcLoS_LoS / 100;      //(11)

        double Lces;

        if (p > pLoS) {

            double pp = (p - pLoS) / (100.0 - pLoS);  // (12 a)

    // Table 9

            double alpha1 = 8.54;
            double alpha2 = 0.056;
            double beta1 = 17.57;
            double beta2 = 6.32;
            double gamma1 = 0.63;
            double gamma2 = 0.19;

            double mu = alpha1 + beta1 * Math.log(1 + (90 - theta) / 90.0) + Math.pow(f, gamma1);      //(13 a)
            double sigma = alpha2 + beta2 * Math.log(1 + (90 - theta) / 90.0) + Math.pow(f, gamma2);   //(13 b)

    //Qinv(1 - pp) = Finv(pp)
                    double Finv = norminv(pp, mu, sigma);

            Lces = Math.max(Finv, 6);


        } else if (p <= pFcLoS) {

                //Table 10

            double theta1 = -3.542;
            double theta2 = -155.1;
            double sigma1 = -1.06;
            double sigma2 = -6.342;

            Lces = theta1 * Math.exp(theta2 * p / pFcLoS) + sigma1 * Math.exp(sigma2 * p / pFcLoS);  //(15)

        } else {

            Lces = 6 * (p - pFcLoS) / (pLoS - pFcLoS);   //(14)

        }


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


