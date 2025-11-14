package test;

import main.P2108;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class P2108Test {
    // the results are compared to the MATLAB/Excel implementation of Recommendation ITU-R P.2108-1
    // the test is passed when the results for transmission loss are within 0.01 dB of difference
    // different location percentages, frequencies, distances, and environment categories are tested
    //     Rev   Date        Author                          Description
    //     -------------------------------------------------------------------------------
    //     v0    05MAY17     Ivica Stevanovic, OFCOM         Initial version in Java
    //     v1    15JUL21     Ivica Stevanovic, OFCOM         Aligned with P.2108-1
    //     v2    14NOV25     Ivica Stevanovic, OFCOM         Aligned with DR P.2108-2


    TestUtil util;

    @Before
    public void setup() {
        util = new TestUtil(0.01);
    }

    @Test
    public void test1() {
        P2108 calculator = new P2108();
        double f = 1.500000;
        double h = 2.000000;
        double ws = 27.000000;
        double R = 10.000000;
        int eqnum = 2;
        double expectedResult = 16.000000;
        double result = calculator.cl_loss1(f, h, eqnum, R, ws);
        util.assertDoubleEquals(expectedResult, result);
    }


    @Test

    public void test2() {
        P2108 calculator = new P2108();
        double f = 1.500000;
        double h = 2.000000;
        double ws = 27.000000;
        double R = 4.000000;
        int eqnum = 2;
        double expectedResult = 6.900000;
        double result = calculator.cl_loss1(f, h, eqnum, R, ws);
        util.assertDoubleEquals(expectedResult, result);
    }


    @Test

    public void test3() {
        P2108 calculator = new P2108();
        double f = 1.500000;
        double h = 2.000000;
        double ws = 27.000000;
        double R = 10.000000;
        int eqnum = 2;
        double expectedResult = 16.000000;
        double result = calculator.cl_loss1(f, h, eqnum, R, ws);
        util.assertDoubleEquals(expectedResult, result);
    }


    @Test

    public void test4() {
        P2108 calculator = new P2108();
        double f = 1.500000;
        double h = 2.000000;
        double ws = 27.000000;
        double R = 4.000000;
        int eqnum = 2;
        double expectedResult = 6.900000;
        double result = calculator.cl_loss1(f, h, eqnum, R, ws);
        util.assertDoubleEquals(expectedResult, result);
    }


    @Test

    public void test5() {
        P2108 calculator = new P2108();
        double f = 1.500000;
        double h = 2.000000;
        double ws = 27.000000;
        double R = 10.000000;
        int eqnum = 1;
        double expectedResult = 20.450000;
        double result = calculator.cl_loss1(f, h, eqnum, R, ws);
        util.assertDoubleEquals(expectedResult, result);
    }


    @Test

    public void test6() {
        P2108 calculator = new P2108();
        double f = 1.500000;
        double h = 2.000000;
        double ws = 27.000000;
        double R = 4.000000;
        int eqnum = 1;
        double expectedResult = 9.240000;
        double result = calculator.cl_loss1(f, h, eqnum, R, ws);
        util.assertDoubleEquals(expectedResult, result);
    }


    @Test

    public void test7() {
        P2108 calculator = new P2108();
        double f = 1.500000;
        double h = 2.000000;
        double ws = 27.000000;
        double R = 15.000000;
        int eqnum = 1;
        double expectedResult = 24.500000;
        double result = calculator.cl_loss1(f, h, eqnum, R, ws);
        util.assertDoubleEquals(expectedResult, result);
    }


    @Test

    public void test8() {
        P2108 calculator = new P2108();
        double f = 1.500000;
        double h = 2.000000;
        double ws = 27.000000;
        double R = 4.000000;
        int eqnum = 1;
        double expectedResult = 9.240000;
        double result = calculator.cl_loss1(f, h, eqnum, R, ws);
        util.assertDoubleEquals(expectedResult, result);
    }


    @Test

    public void test9() {
        P2108 calculator = new P2108();
        double f = 1.500000;
        double h = 2.000000;
        double ws = 27.000000;
        double R = 20.000000;
        int eqnum = 1;
        double expectedResult = 27.100000;
        double result = calculator.cl_loss1(f, h, eqnum, R, ws);
        util.assertDoubleEquals(expectedResult, result);
    }

    @Test

    public void test10() {
        P2108 calculator = new P2108();
        double f = 1.500000;
        double h = 2.000000;
        double ws = 27.000000;
        double R = 4.000000;
        int eqnum = 1;
        double expectedResult = 9.240000;
        double result = calculator.cl_loss1(f, h, eqnum, R, ws);
        util.assertDoubleEquals(expectedResult, result);
    }


    @Test
    public void test11() {
        P2108 calculator = new P2108();
        double[] p = new double[4];
        double[] f = new double[65];
        double d = 0.650000;
        p[0] = 5.000000;
        p[1] = 50.000000;
        p[2] = 95.000000;
        p[3] = 99.900000;
        f[0] = 2.000000;
        f[1] = 3.000000;
        f[2] = 4.000000;
        f[3] = 5.000000;
        f[4] = 6.000000;
        f[5] = 7.000000;
        f[6] = 8.000000;
        f[7] = 9.000000;
        f[8] = 10.000000;
        f[9] = 11.000000;
        f[10] = 12.000000;
        f[11] = 13.000000;
        f[12] = 14.000000;
        f[13] = 15.000000;
        f[14] = 16.000000;
        f[15] = 17.000000;
        f[16] = 18.000000;
        f[17] = 19.000000;
        f[18] = 20.000000;
        f[19] = 21.000000;
        f[20] = 22.000000;
        f[21] = 23.000000;
        f[22] = 24.000000;
        f[23] = 25.000000;
        f[24] = 26.000000;
        f[25] = 27.000000;
        f[26] = 28.000000;
        f[27] = 29.000000;
        f[28] = 30.000000;
        f[29] = 31.000000;
        f[30] = 32.000000;
        f[31] = 33.000000;
        f[32] = 34.000000;
        f[33] = 35.000000;
        f[34] = 36.000000;
        f[35] = 37.000000;
        f[36] = 38.000000;
        f[37] = 39.000000;
        f[38] = 40.000000;
        f[39] = 41.000000;
        f[40] = 42.000000;
        f[41] = 43.000000;
        f[42] = 44.000000;
        f[43] = 45.000000;
        f[44] = 46.000000;
        f[45] = 47.000000;
        f[46] = 48.000000;
        f[47] = 49.000000;
        f[48] = 50.000000;
        f[49] = 51.000000;
        f[50] = 52.000000;
        f[51] = 53.000000;
        f[52] = 54.000000;
        f[53] = 55.000000;
        f[54] = 56.000000;
        f[55] = 57.000000;
        f[56] = 58.000000;
        f[57] = 59.000000;
        f[58] = 60.000000;
        f[59] = 61.000000;
        f[60] = 62.000000;
        f[61] = 63.000000;
        f[62] = 64.000000;
        f[63] = 65.000000;
        f[64] = 66.000000;
        double[] expectedResult = new double[260];
        expectedResult[0] = 19.225939;
        expectedResult[1] = 20.021496;
        expectedResult[2] = 20.484876;
        expectedResult[3] = 20.805425;
        expectedResult[4] = 21.052463;
        expectedResult[5] = 21.255876;
        expectedResult[6] = 21.429856;
        expectedResult[7] = 21.582048;
        expectedResult[8] = 21.717219;
        expectedResult[9] = 21.838664;
        expectedResult[10] = 21.948802;
        expectedResult[11] = 22.049466;
        expectedResult[12] = 22.142080;
        expectedResult[13] = 22.227771;
        expectedResult[14] = 22.307448;
        expectedResult[15] = 22.381853;
        expectedResult[16] = 22.451599;
        expectedResult[17] = 22.517199;
        expectedResult[18] = 22.579086;
        expectedResult[19] = 22.637631;
        expectedResult[20] = 22.693150;
        expectedResult[21] = 22.745919;
        expectedResult[22] = 22.796177;
        expectedResult[23] = 22.844134;
        expectedResult[24] = 22.889975;
        expectedResult[25] = 22.933864;
        expectedResult[26] = 22.975946;
        expectedResult[27] = 23.016352;
        expectedResult[28] = 23.055200;
        expectedResult[29] = 23.092594;
        expectedResult[30] = 23.128629;
        expectedResult[31] = 23.163392;
        expectedResult[32] = 23.196962;
        expectedResult[33] = 23.229410;
        expectedResult[34] = 23.260802;
        expectedResult[35] = 23.291197;
        expectedResult[36] = 23.320651;
        expectedResult[37] = 23.349215;
        expectedResult[38] = 23.376935;
        expectedResult[39] = 23.403856;
        expectedResult[40] = 23.430017;
        expectedResult[41] = 23.455455;
        expectedResult[42] = 23.480205;
        expectedResult[43] = 23.504300;
        expectedResult[44] = 23.527770;
        expectedResult[45] = 23.550643;
        expectedResult[46] = 23.572946;
        expectedResult[47] = 23.594702;
        expectedResult[48] = 23.615935;
        expectedResult[49] = 23.636668;
        expectedResult[50] = 23.656920;
        expectedResult[51] = 23.676711;
        expectedResult[52] = 23.696059;
        expectedResult[53] = 23.714981;
        expectedResult[54] = 23.733494;
        expectedResult[55] = 23.751612;
        expectedResult[56] = 23.769350;
        expectedResult[57] = 23.786723;
        expectedResult[58] = 23.803743;
        expectedResult[59] = 23.820422;
        expectedResult[60] = 23.836772;
        expectedResult[61] = 23.852805;
        expectedResult[62] = 23.868530;
        expectedResult[63] = 23.883959;
        expectedResult[64] = 23.899101;
        expectedResult[65] = 27.092893;
        expectedResult[66] = 28.337956;
        expectedResult[67] = 29.098083;
        expectedResult[68] = 29.599378;
        expectedResult[69] = 29.940271;
        expectedResult[70] = 30.178499;
        expectedResult[71] = 30.352541;
        expectedResult[72] = 30.486760;
        expectedResult[73] = 30.595505;
        expectedResult[74] = 30.687053;
        expectedResult[75] = 30.766304;
        expectedResult[76] = 30.836298;
        expectedResult[77] = 30.899034;
        expectedResult[78] = 30.955890;
        expectedResult[79] = 31.007864;
        expectedResult[80] = 31.055706;
        expectedResult[81] = 31.099996;
        expectedResult[82] = 31.141194;
        expectedResult[83] = 31.179676;
        expectedResult[84] = 31.215748;
        expectedResult[85] = 31.249671;
        expectedResult[86] = 31.281661;
        expectedResult[87] = 31.311907;
        expectedResult[88] = 31.340568;
        expectedResult[89] = 31.367785;
        expectedResult[90] = 31.393682;
        expectedResult[91] = 31.418365;
        expectedResult[92] = 31.441930;
        expectedResult[93] = 31.464463;
        expectedResult[94] = 31.486039;
        expectedResult[95] = 31.506726;
        expectedResult[96] = 31.526586;
        expectedResult[97] = 31.545674;
        expectedResult[98] = 31.564040;
        expectedResult[99] = 31.581730;
        expectedResult[100] = 31.598785;
        expectedResult[101] = 31.615244;
        expectedResult[102] = 31.631141;
        expectedResult[103] = 31.646509;
        expectedResult[104] = 31.661377;
        expectedResult[105] = 31.675771;
        expectedResult[106] = 31.689718;
        expectedResult[107] = 31.703240;
        expectedResult[108] = 31.716359;
        expectedResult[109] = 31.729095;
        expectedResult[110] = 31.741467;
        expectedResult[111] = 31.753492;
        expectedResult[112] = 31.765186;
        expectedResult[113] = 31.776564;
        expectedResult[114] = 31.787641;
        expectedResult[115] = 31.798430;
        expectedResult[116] = 31.808943;
        expectedResult[117] = 31.819193;
        expectedResult[118] = 31.829189;
        expectedResult[119] = 31.838943;
        expectedResult[120] = 31.848465;
        expectedResult[121] = 31.857763;
        expectedResult[122] = 31.866846;
        expectedResult[123] = 31.875723;
        expectedResult[124] = 31.884401;
        expectedResult[125] = 31.892888;
        expectedResult[126] = 31.901191;
        expectedResult[127] = 31.909316;
        expectedResult[128] = 31.917270;
        expectedResult[129] = 31.925059;
        expectedResult[130] = 34.591634;
        expectedResult[131] = 36.337952;
        expectedResult[132] = 37.527167;
        expectedResult[133] = 38.347939;
        expectedResult[134] = 38.828079;
        expectedResult[135] = 39.101121;
        expectedResult[136] = 39.275226;
        expectedResult[137] = 39.391473;
        expectedResult[138] = 39.473792;
        expectedResult[139] = 39.535443;
        expectedResult[140] = 39.560709;
        expectedResult[141] = 39.571208;
        expectedResult[142] = 39.577700;
        expectedResult[143] = 39.581797;
        expectedResult[144] = 39.584421;
        expectedResult[145] = 39.586115;
        expectedResult[146] = 39.587210;
        expectedResult[147] = 39.587910;
        expectedResult[148] = 39.588346;
        expectedResult[149] = 39.588604;
        expectedResult[150] = 39.588739;
        expectedResult[151] = 39.588790;
        expectedResult[152] = 39.588782;
        expectedResult[153] = 39.588734;
        expectedResult[154] = 39.588658;
        expectedResult[155] = 39.588564;
        expectedResult[156] = 39.588456;
        expectedResult[157] = 39.588341;
        expectedResult[158] = 39.588221;
        expectedResult[159] = 39.588099;
        expectedResult[160] = 39.587976;
        expectedResult[161] = 39.587853;
        expectedResult[162] = 39.587732;
        expectedResult[163] = 39.587612;
        expectedResult[164] = 39.587495;
        expectedResult[165] = 39.587381;
        expectedResult[166] = 39.587269;
        expectedResult[167] = 39.587161;
        expectedResult[168] = 39.587055;
        expectedResult[169] = 39.586952;
        expectedResult[170] = 39.586852;
        expectedResult[171] = 39.586755;
        expectedResult[172] = 39.586661;
        expectedResult[173] = 39.586570;
        expectedResult[174] = 39.586481;
        expectedResult[175] = 39.586395;
        expectedResult[176] = 39.586312;
        expectedResult[177] = 39.586230;
        expectedResult[178] = 39.586152;
        expectedResult[179] = 39.586075;
        expectedResult[180] = 39.586000;
        expectedResult[181] = 39.585928;
        expectedResult[182] = 39.585857;
        expectedResult[183] = 39.585789;
        expectedResult[184] = 39.585722;
        expectedResult[185] = 39.585657;
        expectedResult[186] = 39.585594;
        expectedResult[187] = 39.585532;
        expectedResult[188] = 39.585472;
        expectedResult[189] = 39.585414;
        expectedResult[190] = 39.585356;
        expectedResult[191] = 39.585301;
        expectedResult[192] = 39.585246;
        expectedResult[193] = 39.585193;
        expectedResult[194] = 39.585141;
        expectedResult[195] = 40.381908;
        expectedResult[196] = 42.134756;
        expectedResult[197] = 43.330835;
        expectedResult[198] = 44.157628;
        expectedResult[199] = 44.692072;
        expectedResult[200] = 45.007276;
        expectedResult[201] = 45.181459;
        expectedResult[202] = 45.275560;
        expectedResult[203] = 45.326771;
        expectedResult[204] = 45.355206;
        expectedResult[205] = 45.371333;
        expectedResult[206] = 45.380622;
        expectedResult[207] = 45.385999;
        expectedResult[208] = 45.389074;
        expectedResult[209] = 45.390764;
        expectedResult[210] = 45.391603;
        expectedResult[211] = 45.391912;
        expectedResult[212] = 45.391889;
        expectedResult[213] = 45.391657;
        expectedResult[214] = 45.391297;
        expectedResult[215] = 45.390857;
        expectedResult[216] = 45.390373;
        expectedResult[217] = 45.389865;
        expectedResult[218] = 45.389349;
        expectedResult[219] = 45.388833;
        expectedResult[220] = 45.388325;
        expectedResult[221] = 45.387827;
        expectedResult[222] = 45.387343;
        expectedResult[223] = 45.386873;
        expectedResult[224] = 45.386419;
        expectedResult[225] = 45.385981;
        expectedResult[226] = 45.385559;
        expectedResult[227] = 45.385152;
        expectedResult[228] = 45.384760;
        expectedResult[229] = 45.384383;
        expectedResult[230] = 45.384019;
        expectedResult[231] = 45.383669;
        expectedResult[232] = 45.383331;
        expectedResult[233] = 45.383006;
        expectedResult[234] = 45.382692;
        expectedResult[235] = 45.382389;
        expectedResult[236] = 45.382097;
        expectedResult[237] = 45.381814;
        expectedResult[238] = 45.381541;
        expectedResult[239] = 45.381277;
        expectedResult[240] = 45.381022;
        expectedResult[241] = 45.380774;
        expectedResult[242] = 45.380535;
        expectedResult[243] = 45.380302;
        expectedResult[244] = 45.380077;
        expectedResult[245] = 45.379859;
        expectedResult[246] = 45.379647;
        expectedResult[247] = 45.379441;
        expectedResult[248] = 45.379240;
        expectedResult[249] = 45.379046;
        expectedResult[250] = 45.378857;
        expectedResult[251] = 45.378673;
        expectedResult[252] = 45.378493;
        expectedResult[253] = 45.378319;
        expectedResult[254] = 45.378149;
        expectedResult[255] = 45.377983;
        expectedResult[256] = 45.377822;
        expectedResult[257] = 45.377664;
        expectedResult[258] = 45.377511;
        expectedResult[259] = 45.377361;
        int count = 0;
        for (int pi = 0; pi < 4; pi++) {
            for (int fi = 0; fi < 65; fi++) {
                double result = calculator.cl_loss2(f[fi], d, p[pi]);
                util.assertDoubleEquals(expectedResult[count], result);
                count = count + 1;
            }
        }
    }



    @Test
    public void test12() {

        P2108 calculator = new P2108();

        int sizeY = 0;

        // path to the directory where profiles are located
        String directoryPath = "src/test/validation_examples/";

        // Using File class create an object for specific directory
        File directory = new File(directoryPath);

        // Using listFiles method we get all the files of a directory
        // return type of listFiles is array
        File[] files = directory.listFiles();

        // Get name of the all files present in that path
        if (files != null) {
            for (File file : files) {
                List<String> lines_r = new ArrayList<>();
                String directoryPath_r = "src/test/validation_examples/";

                String file_rel_r = directoryPath_r + "validation_example_p2108_3.csv";
                //System.out.println(file_rel_r);

                // read all the lines from the profile
                try {

                    InputStream inputStream_r = new FileInputStream(file_rel_r);

                    // read the reference results next


                    InputStreamReader inputStreamReader_r = new InputStreamReader(inputStream_r);
                    BufferedReader br_r = new BufferedReader(inputStreamReader_r);
                    String line_r;
                    while (null != (line_r = br_r.readLine())) {

                        lines_r.add(line_r);
                    }

                    sizeY = lines_r.size();
                    inputStream_r.close();

                    for (int i = 1; i < sizeY; i++) { /* DO */

                        String[] parts = lines_r.get(i).trim().split(",");

                        double f = Double.parseDouble(parts[0]);
                        double theta = Double.parseDouble(parts[1]);

                        double p = Double.parseDouble(parts[2]);
                        double h = Double.parseDouble(parts[3]);
                        double hm = Double.parseDouble(parts[4]);
                        double Lces_ref = Double.parseDouble(parts[5]);

                        double result = calculator.cl_loss3(f, theta, p, h, hm);
                        util.assertDoubleEquals(Lces_ref, result);

                    }


                } catch (Exception ex) {

                    throw new IllegalArgumentException("Could not load the file: '" + file_rel_r + "'");
                }


            }


        } else {
            System.out.println("Did not find any files");
        }


    }

}

