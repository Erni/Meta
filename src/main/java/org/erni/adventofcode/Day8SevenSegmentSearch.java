package org.erni.adventofcode;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.stream.Collectors;

public class Day8SevenSegmentSearch {

    static String[][] digits = {
            {"beacf", "afbd", "bcead", "cgefa", "ecdbga", "efb", "gbfdeac", "ecgfbd", "acbdfe", "fb"},
            {"cbdgef", "cdfageb", "cegbd", "fecba", "edgf", "dcfbe", "dbcafg", "df", "fcd", "dbegca"},
            {"bgdcfa", "dgabf", "bfdec", "baefd", "abeg", "eda", "afcdge", "ea", "bfceadg", "dfegba"},
            {"cfdabe", "fcgbe", "gcdeb", "ecdfgb", "bdc", "cdgf", "cd", "cfageb", "bgdae", "daebfcg"},
            {"eacgdfb", "fdbeca", "agedc", "cbedf", "dcfgab", "bgef", "gdf", "fdgec", "fbcged", "gf"},
            {"egcd", "aebdcf", "gbeca", "eg", "edfcbga", "gbe", "cfbag", "bdeca", "gadbec", "bdgefa"},
            {"fcgab", "egafb", "gedbca", "gfc", "cf", "ceagdf", "cafbdeg", "gcafbd", "dbagc", "cfdb"},
            {"geacd", "bg", "cbfead", "efgabcd", "fbace", "ceabg", "efbadg", "gfbc", "gab", "geabcf"},
            {"bfceg", "ca", "baefc", "caedfb", "acfedgb", "cfad", "edacbg", "eac", "debaf", "bdfage"},
            {"fd", "dcfbge", "fcad", "dfg", "bgaefc", "fdcgab", "fgdceab", "afdbg", "cbagf", "gdaeb"},
            {"dabce", "gceab", "fegcab", "fbdca", "adeg", "gcabdfe", "gedbac", "ed", "egdfbc", "deb"},
            {"bgcea", "cbfdage", "egad", "ecadb", "beg", "befdcg", "fcgba", "aecbdf", "ge", "abedcg"},
            {"bfecag", "egbdfac", "abecg", "gcebf", "efdagc", "ca", "acbf", "cfbegd", "eac", "bgaed"},
            {"fcbed", "dbfegca", "cdafbe", "edfgc", "abfde", "ebc", "egcfab", "dbca", "fdgbea", "cb"},
            {"fb", "fdeacb", "bfe", "bfdc", "ebcgda", "afdeb", "acbfge", "agedf", "ecbad", "fdagcbe"},
            {"egbca", "cfe", "eadbfg", "baedgfc", "cf", "bcfd", "afbde", "acebf", "fecdag", "edafbc"},
            {"gfabc", "fbcaeg", "bagd", "cgbfaed", "dfecb", "gfdcb", "fcedga", "dg", "dafbgc", "dgf"},
            {"cdb", "abce", "ebgfdc", "bfagdec", "abdcef", "dbfea", "gfdca", "facbd", "dfaebg", "cb"},
            {"afdbe", "dcfegb", "gedca", "adcbe", "ecfgbad", "fcadge", "cb", "acgdeb", "cbe", "agcb"},
            {"gafdec", "cbdg", "fdgbce", "dce", "dfegb", "fceab", "becagdf", "dc", "dfbce", "agdefb"},
            {"bagfc", "bdef", "cgeafd", "eagfbcd", "afceb", "fe", "cfabde", "agcdeb", "cef", "eabdc"},
            {"fbage", "cgfba", "bdcage", "edagbf", "fe", "agcfdeb", "gfe", "adfe", "dgabe", "egbfdc"},
            {"gdfec", "bgfc", "dbgafe", "gfdebc", "dbfeg", "fc", "fabdcge", "fcd", "gedac", "dcbfea"},
            {"dacgf", "dfabc", "ebgcad", "daegfcb", "aedfbc", "dbgf", "agd", "aegfc", "cbfdag", "gd"},
            {"gdbea", "ebacdf", "adebfgc", "dfb", "df", "fbdea", "gcfebd", "fceab", "bfcgea", "fdac"},
            {"fba", "gbdcf", "cbae", "ebfgca", "fbcegda", "ab", "gbedfa", "gbacf", "gface", "gecdfa"},
            {"fgebda", "bfgae", "cfdgba", "agcbe", "dbaegcf", "gbfda", "ef", "bfe", "gcbdfe", "dfea"},
            {"agfecb", "cabef", "dafgec", "adfeb", "fbc", "gacb", "cb", "afbegdc", "cfaeg", "cbdefg"},
            {"agf", "eadfc", "cegabfd", "eagdf", "efdgb", "cgfbde", "agbfed", "gfbcae", "ag", "abdg"},
            {"gfbdac", "bd", "gcaefd", "bdcegfa", "gadbce", "dgb", "badf", "gbfdc", "fgecb", "cgadf"},
            {"cbgf", "efcgbd", "aefdcbg", "feabd", "dcf", "cdebf", "ebcgda", "cf", "egcbd", "fadceg"},
            {"bacgfe", "fbc", "cgfe", "bcdagf", "gebfa", "cafeb", "bdcafge", "afdegb", "caedb", "cf"},
            {"bgdf", "geacf", "acedgb", "gb", "aecbfd", "bdcfa", "abgcf", "bgc", "acgefdb", "cdgabf"},
            {"bdacgef", "fadeb", "abcg", "gfcebd", "gbf", "afbge", "egacf", "dgceaf", "bg", "aefgcb"},
            {"dcegf", "bg", "cdafegb", "cfdebg", "fagced", "bge", "ecfab", "dbgace", "cgbef", "bdgf"},
            {"gbf", "fbcdega", "egdafc", "bg", "aefgd", "gbecfd", "agbe", "abcdf", "edgafb", "fbdag"},
            {"beaf", "aed", "aecdf", "aebcgd", "eafdcb", "cgfabd", "ea", "gfcde", "cbdfa", "ceagdfb"},
            {"bdcfe", "gbdae", "bcgafd", "daegfb", "aecg", "cdagbe", "gfbadec", "gbc", "gc", "edcgb"},
            {"ec", "fdcag", "aegcbf", "bagfcd", "cgfade", "adecg", "fdaebcg", "fecd", "degba", "egc"},
            {"dcgaeb", "egadc", "agcfde", "efac", "beagdfc", "fcdagb", "dgcef", "gbfde", "dfc", "cf"},
            {"afd", "df", "gdafecb", "bdcf", "fecba", "acged", "edcabf", "egdbfa", "egfabc", "acdfe"},
            {"ga", "beafc", "cgbea", "dceagf", "feabdc", "bfagec", "abgf", "cga", "fdeagbc", "cebdg"},
            {"edag", "cdafb", "ae", "efacd", "eacgbf", "cedgbf", "afe", "fbceadg", "cdgaef", "egcdf"},
            {"eagdbfc", "cagdf", "badcge", "fgec", "cg", "cdg", "bdfca", "feadg", "fecdga", "dfbega"},
            {"beadg", "ca", "cdeba", "gcda", "acfebg", "dfbce", "fcbadge", "dbafge", "gdabec", "cae"},
            {"agfcde", "gdceab", "dbeaf", "agfed", "dg", "acfegb", "gaedbfc", "adg", "aegcf", "fgcd"},
            {"gafcdb", "bfgaec", "daecf", "bcfgdea", "bged", "be", "dcgfb", "cfebd", "fbe", "dfebgc"},
            {"dcaef", "agfbec", "gea", "ge", "bged", "cbdfag", "fgbade", "gbdefca", "abfgd", "faged"},
            {"dcbfa", "ag", "fcaegb", "fcdaegb", "gebdf", "adeg", "bdfga", "dgfeba", "ebcdgf", "gaf"},
            {"bcgdaef", "fcabde", "dbfage", "cfdgb", "daegf", "bfe", "dgefb", "gfadce", "egab", "eb"},
            {"be", "egb", "fgcbde", "agdbcf", "gcdfb", "edbf", "bagecf", "ecadg", "bdefgca", "bdegc"},
            {"bcegfd", "ceafd", "gbefca", "eba", "dacebfg", "gebfd", "baegdf", "dabg", "dbfae", "ab"},
            {"dceagfb", "edac", "ecfba", "cbgadf", "dfbge", "bcdefa", "fdc", "cd", "fcaegb", "edcbf"},
            {"cgaedbf", "acd", "afbdge", "afdcg", "bdcaeg", "gadfb", "ac", "gcbdfa", "abcf", "cdgef"},
            {"gaefdcb", "debga", "cd", "aebcd", "gceafb", "afdceb", "cfabe", "dfcb", "dca", "agdcef"},
            {"abdeg", "eaf", "cedfba", "aefdg", "gcadfe", "af", "gdfaecb", "gcfa", "gdfec", "cbgdef"},
            {"bcgdfe", "fbdce", "fcdbea", "bcead", "adc", "ca", "bdgcaf", "edcgbaf", "baged", "feac"},
            {"bd", "aecfgb", "bfad", "gdb", "dgbace", "fbaegdc", "egbaf", "ecdgf", "egbdf", "fgadeb"},
            {"bfcea", "dfbcea", "badge", "bfegdca", "gac", "cafdgb", "gc", "aecbgf", "caebg", "gefc"},
            {"cagef", "fadgebc", "fd", "daf", "dcfbga", "ebdgfa", "cbeadg", "afdeg", "abegd", "ebdf"},
            {"dfaebc", "bcgaf", "bcaeg", "abecgf", "eagf", "dgecb", "cae", "ae", "dcgbafe", "gbadcf"},
            {"dfbec", "gcbade", "fadb", "df", "badefcg", "dbeac", "cfgeb", "ebafcd", "fecdga", "edf"},
            {"cadgfe", "bdfea", "dgbfeca", "dab", "dgbaec", "afdegb", "fdgb", "gaefd", "db", "cbfae"},
            {"decbga", "ebfcgd", "gcebf", "cabfe", "aec", "cbfad", "ea", "eafg", "adbgcef", "cebfag"},
            {"dcgefb", "gafc", "bagfce", "ca", "fcaeb", "aec", "dfbae", "ebgcf", "bcfdega", "deacgb"},
            {"gafc", "dgafecb", "cdgeba", "dbgafc", "fa", "bacdg", "fceabd", "fbgde", "afb", "fadgb"},
            {"dgfe", "dfa", "fd", "agcfbd", "bedac", "gfaec", "bdfcgae", "ebgcaf", "edfac", "acegfd"},
            {"efgacdb", "gedfc", "eadbfg", "efd", "dcagf", "cfdage", "gdbacf", "gcbed", "fe", "fcae"},
            {"aebg", "gbfdac", "dcebfga", "bdcef", "ebcfag", "abcfg", "acfeb", "dfcaeg", "ea", "eca"},
            {"ceag", "caefgb", "agb", "fbgdc", "ag", "cafbg", "becfa", "cfeabd", "deafgb", "eafcgbd"},
            {"bdge", "agbec", "dgcfae", "agbedcf", "gdc", "gbecad", "dabgc", "gd", "fbadc", "abefcg"},
            {"dcef", "efb", "dbcfgae", "gbecfa", "fe", "ebcdag", "cafedb", "cadbe", "afdbe", "badfg"},
            {"cdagfeb", "fgabc", "cafd", "fcb", "bdagc", "dfcgeb", "gbfacd", "cf", "baefg", "bdegac"},
            {"cefbdg", "debgca", "abfeg", "cebgf", "fdbgac", "adcgefb", "fcde", "cbe", "ce", "cdbgf"},
            {"fcbag", "gcfbed", "adgbefc", "afbdeg", "adbgf", "dbea", "ad", "cefagd", "daf", "debfg"},
            {"ba", "gbcda", "degcb", "bad", "adbegc", "fgcad", "eacb", "adgefb", "gdfaecb", "gcbedf"},
            {"cdabf", "cbdgae", "facgb", "fadg", "acg", "gfbce", "ag", "cgdafb", "edcfab", "dbcegfa"},
            {"edbaf", "cfebad", "aec", "cdaf", "ca", "gfabce", "cbeda", "afgebd", "fbdacge", "bcgde"},
            {"egdba", "bafeg", "bd", "gdfeba", "bcedfa", "agcde", "bad", "fgdb", "fceabg", "gabdefc"},
            {"fdcgae", "dcefb", "cdb", "fecgabd", "afecb", "dbcfeg", "bgceda", "bd", "gfdb", "cdegf"},
            {"beg", "eg", "bfcea", "cdfgeb", "baefg", "abgdf", "fgeabd", "gcfdba", "agde", "dgbaefc"},
            {"dbgef", "bfecgad", "egfcd", "agfbce", "agbde", "bf", "gcebdf", "dagfce", "cbdf", "feb"},
            {"abgdec", "fgd", "fcgeb", "gedabf", "gfbdc", "dgaecbf", "afdc", "dgcafb", "fd", "bgcad"},
            {"cafdb", "acdbge", "acedb", "dfbcg", "af", "ceadfbg", "adf", "adfgec", "faeb", "dcfeab"},
            {"dbcgae", "bcdfeg", "bfceadg", "bafgcd", "dacgb", "dfac", "cfgba", "bfc", "aefgb", "cf"},
            {"geacb", "gebfda", "gbcdafe", "efgc", "ge", "ecabd", "bcefag", "gfbcda", "age", "gafbc"},
            {"gabce", "feagdb", "dgb", "fceadb", "deagb", "edbfa", "gd", "cbfgde", "agdbfec", "afdg"},
            {"afecbg", "fgeda", "dfeabgc", "cdafe", "adgb", "dg", "dbcegf", "fabged", "faebg", "egd"},
            {"ec", "badfec", "cfe", "befgd", "gdacfe", "bace", "fgedbac", "gbcadf", "fdcba", "cfedb"},
            {"dacfgb", "fd", "gfdbae", "ecbdgfa", "ecbdag", "cbfeg", "faed", "gdf", "aegbd", "egdfb"},
            {"faegbd", "fgdace", "acbfeg", "dfgecba", "ed", "dabge", "fdbe", "dae", "badcg", "eagbf"},
            {"dbe", "gbcef", "acdebf", "defa", "geabdc", "de", "dfcbag", "dbgaecf", "cabfd", "becdf"},
            {"gcaed", "adgbce", "abfeg", "cb", "egcba", "cdba", "cgadef", "cgbefda", "fdbegc", "ebc"},
            {"cef", "fbacd", "gafedc", "ec", "gaecbdf", "bfedg", "gdcfeb", "bfdce", "cegb", "bfadeg"},
            {"gfdae", "adgb", "cabfde", "fgdceb", "dea", "gafec", "bdgafec", "agdebf", "ad", "degfb"},
            {"ec", "cge", "dagef", "eacgbf", "cdafegb", "cfgadb", "gbedca", "ecagd", "cdeb", "agcbd"},
            {"ebgac", "cegbad", "dfcegb", "abegdf", "gbdec", "bae", "dcae", "efabgdc", "ea", "cgfba"},
            {"dcgfba", "adgbef", "feac", "afedgc", "dcegf", "egabfdc", "fc", "fdaeg", "bdceg", "dcf"},
            {"cadfg", "gb", "cedagf", "gbd", "abgcfd", "fdcebga", "acgb", "febgda", "fdbec", "bdgcf"},
            {"fgdca", "afcdeg", "bcdeagf", "gc", "fcaed", "defabc", "aedcbg", "bgfad", "cdg", "cfge"},
            {"fea", "adgeb", "bgfe", "fe", "dcfeag", "bafegd", "badcf", "aedbfgc", "adefb", "bcdeag"},
            {"gace", "dagcf", "fedac", "fbceagd", "defab", "cfe", "gdcfeb", "cdfgae", "ec", "gbfadc"},
            {"afc", "gaedf", "dfecga", "bafcdge", "cf", "bgdac", "dafcg", "eacbfd", "gbdafe", "gcef"},
            {"cdfebg", "dcbe", "fgbdac", "bfegd", "be", "beagfc", "gfdea", "gacfbed", "fbe", "cgfbd"},
            {"afg", "dfgec", "gcfeab", "ebcfagd", "fa", "adfe", "bagcd", "gdcfa", "gfbedc", "gecdfa"},
            {"bfdag", "gbecd", "cefg", "efcbdga", "fbc", "cf", "gbcfd", "badfec", "bedfcg", "egcbda"},
            {"cafde", "dce", "dcbfa", "dgcefb", "cgea", "dgafceb", "fgcdae", "dagfe", "gfebad", "ec"},
            {"gcebad", "cfgdb", "cfgeab", "cdb", "cd", "egcdbf", "gbdaf", "fcde", "fgdbcae", "gfbec"},
            {"afegb", "adbfg", "becafdg", "dg", "bgd", "gaebfc", "fdge", "cdfba", "gbcead", "abfdeg"},
            {"fabeg", "gd", "dfebcg", "dgfae", "bfgeca", "fecad", "adgb", "efabgd", "ecfdbag", "deg"},
            {"bfceag", "dafgb", "fdce", "fc", "cfg", "gcdebf", "becgfda", "dgbec", "aedcgb", "cbgdf"},
            {"bfeg", "decaf", "cegfa", "ecfgab", "gcf", "dbfeacg", "caedgb", "aegbc", "dbcafg", "fg"},
            {"eac", "ae", "cbdfe", "cbaedf", "abcfedg", "bade", "aefcbg", "bdcgef", "cedaf", "gdfca"},
            {"gdebfca", "bfgca", "fda", "bcafd", "ad", "gcda", "cfgdab", "fadbeg", "edfcb", "gcbfae"},
            {"gfbdcae", "afed", "gedac", "facegd", "bgeac", "de", "fgbecd", "cagfd", "ged", "bdgfca"},
            {"gdafbe", "dbacgf", "cbefag", "cbfdg", "bfdag", "fcedg", "cdbgfae", "adbc", "cgb", "cb"},
            {"fa", "egcad", "acfed", "ebdcfa", "dgcbef", "daf", "bdecf", "fgadbc", "fbae", "gabdecf"},
            {"cfd", "fd", "gbafc", "dcagf", "daecg", "dbfa", "cbfage", "egfdbc", "bfdgcae", "cbdfga"},
            {"eafbdgc", "baegd", "fe", "bagfed", "aebfg", "cabedg", "efg", "facgb", "bdgfec", "fade"},
            {"cfdeagb", "bdeacf", "eac", "gcdbfa", "egab", "egacfb", "cdgfe", "ea", "ecfga", "cgfba"},
            {"aefcdgb", "bacfg", "cdg", "afecd", "gedbcf", "afgcbe", "abgd", "adbfcg", "fgdac", "dg"},
            {"becf", "baefgd", "dcafg", "cb", "cdbage", "dcgfb", "bcg", "gdbef", "edbfcg", "egbcafd"},
            {"gb", "bgedca", "fedga", "cfdeag", "fegb", "dcbfa", "gab", "afgdb", "acbgefd", "bdgaef"},
            {"dca", "cedbf", "dcfebga", "ca", "egbcad", "cdaefb", "fedag", "efdac", "acbf", "cfgdbe"},
            {"bgdeafc", "gaefd", "fbeagc", "cdfa", "fgc", "degcf", "dgbafe", "gcedaf", "dgcbe", "fc"},
            {"dfaeb", "fae", "ebacfd", "egadb", "fe", "gadbfc", "febc", "gbfeacd", "fagcde", "dcabf"},
            {"fgaed", "gd", "dacgfb", "egcd", "gdf", "acfed", "adbfceg", "edbfca", "dcafeg", "baefg"},
            {"bcfeadg", "dfagce", "fdcgba", "dfcab", "gabec", "gcf", "gf", "abgfc", "bacedf", "bdgf"},
            {"ceab", "gafec", "cef", "fcdgba", "cbfeadg", "bcagf", "ce", "ceagbf", "agdfe", "degcbf"},
            {"abgef", "abf", "gbeac", "gabfcde", "degcfb", "efdbga", "aefd", "bgdcfa", "af", "befdg"},
            {"gdafce", "cfb", "agfbc", "ebaf", "fcebdg", "aegfc", "cbgfea", "egbfdca", "dbgac", "bf"},
            {"ceadf", "fecag", "dcgaeb", "dgbfcea", "edc", "bcafd", "cdabef", "ed", "befd", "gfcdab"},
            {"gecdbaf", "dbag", "bfade", "gabfed", "cedfba", "gfade", "cfaebg", "fgdce", "ag", "fga"},
            {"defbcg", "gbfce", "beag", "gacdf", "cfbaeg", "dbcfea", "ea", "cefga", "ceafbgd", "ace"},
            {"egbdac", "fgadb", "cefdbg", "aceg", "gc", "cedba", "dcbag", "ecafdb", "fgeacdb", "gcd"},
            {"bdac", "ac", "fcdaebg", "afbdeg", "dbagf", "fbcge", "acf", "cadgbf", "afedgc", "bgfca"},
            {"ba", "eacfg", "cab", "aefcb", "cdeabgf", "ecgdbf", "dcbfe", "dbfeac", "acdbeg", "dfab"},
            {"gbfca", "ceafbd", "geda", "gcdfae", "efbcdg", "fgced", "da", "degbcfa", "adf", "cdagf"},
            {"fgdacb", "dg", "efagc", "gcd", "cgefab", "edcgfa", "cbedf", "gfdce", "eabgcfd", "daeg"},
            {"bcadeg", "fbgaec", "ecdag", "gbafced", "ba", "bca", "edab", "bgadc", "fdcgae", "gdbcf"},
            {"aedfb", "dbfcae", "caefbdg", "aefcdg", "gafcb", "dfcba", "bgfeda", "dc", "fdc", "edcb"},
            {"cga", "afdebc", "gc", "adcgefb", "adbgf", "acgfde", "gcabd", "gbedac", "ecgb", "acdeb"},
            {"cdf", "dcafg", "df", "cgade", "cebgad", "dcgfea", "fdacbeg", "dgbfec", "afbgc", "fade"},
            {"fagbc", "dbag", "feadgc", "gfa", "dcebfg", "gbdcf", "ag", "gecafbd", "ceafb", "bagcfd"},
            {"cadbfeg", "dbgace", "fcagd", "bedfca", "cfegd", "afgb", "bgdfca", "ga", "adg", "fdabc"},
            {"fgeabd", "egbca", "dfage", "fdagec", "fb", "dacbfe", "badgecf", "baf", "gfaeb", "bdgf"},
            {"gbdaefc", "fa", "dgafb", "feda", "fegbdc", "dfbeg", "gaf", "cegfba", "gacdb", "badgfe"},
            {"bedc", "fbagd", "bdage", "cgdeab", "eba", "gcabef", "afcdge", "edgca", "be", "cfbgeda"},
            {"fe", "fceb", "acegdfb", "efg", "fdgce", "fagbed", "bedgc", "fedbgc", "dagbce", "agdfc"},
            {"fagdc", "ebacgd", "ecgdb", "decafgb", "cefbda", "geba", "bagcd", "bad", "fbdgce", "ba"},
            {"decgaf", "cgab", "aedbc", "cbaegd", "ca", "bdegc", "fcegdab", "efabd", "cda", "cdbfeg"},
            {"gafbde", "ecfda", "afdegc", "ecdgb", "ba", "cbfa", "cfadeb", "edabc", "bea", "ecbdfga"},
            {"gbecaf", "dbgf", "facbgde", "cegabd", "gebdfa", "fdaec", "dfage", "egf", "adebg", "fg"},
            {"gfceabd", "adgcb", "bcdf", "afb", "gafbd", "becafg", "bf", "dagef", "agbdec", "fgcdba"},
            {"ba", "afegdb", "cdab", "cfgeb", "facebdg", "fcabg", "cdagf", "fdgcab", "gab", "decgfa"},
            {"adebgc", "bdefcga", "afegdb", "bcedf", "bfg", "fedbg", "fg", "efga", "dbgea", "facdbg"},
            {"cegfda", "gfa", "ecgf", "gf", "ebgdac", "afcbd", "ecagd", "afebgd", "fcdag", "begcafd"},
            {"febgcd", "fcbda", "cdgab", "fcgbad", "bf", "fdcea", "dfb", "egbadfc", "bgcdae", "fbag"},
            {"ebfag", "fcbade", "ad", "adec", "bgecdfa", "afd", "gebfcd", "bfdec", "fbade", "bgcdfa"},
            {"cafbgde", "bdace", "cefadg", "gefcbd", "ag", "egdcf", "gae", "bcfaeg", "gcdae", "adfg"},
            {"agcef", "egcab", "fc", "dfeagb", "fce", "gbaefdc", "ebcadf", "aefdg", "adefcg", "gcfd"},
            {"db", "fdbcge", "dgb", "gfaced", "bfacgde", "dfba", "egdfba", "cabge", "edgfa", "gebda"},
            {"cad", "cdfgea", "caegbf", "agbd", "cbdef", "gbcdaf", "gadecbf", "ad", "bcafg", "dacbf"},
            {"bdegaf", "gfde", "gf", "bcadeg", "dafgcb", "afg", "bgaef", "eagdb", "cfbeadg", "fbeac"},
            {"bgac", "edgacbf", "dfbea", "cgebda", "gea", "cfedga", "agedb", "ag", "dcgeb", "fcedgb"},
            {"caebd", "edf", "edgfbc", "fe", "abfegdc", "cdfea", "gfdac", "eacfgd", "gfacbd", "gaef"},
            {"bfadegc", "dafec", "dbgaec", "abdcef", "fdaegc", "dag", "cagf", "ga", "aedgf", "fbedg"},
            {"dcgfa", "dgcafeb", "cfb", "bf", "cegafb", "begcda", "fdgbce", "dcebg", "bfed", "fcgdb"},
            {"cbegd", "edfa", "fdc", "gadcfb", "gbcefad", "bgacef", "acfbe", "ebacfd", "bfecd", "fd"},
            {"fgd", "dgbfe", "gf", "gcef", "bdecf", "dgcfba", "acfbde", "gdefbc", "badge", "facbdeg"},
            {"abdeg", "adebcg", "ebafdc", "bgcadfe", "gedc", "ega", "bgdaf", "cabed", "efacbg", "ge"},
            {"debfa", "dfbga", "fagdc", "fbg", "cadgfe", "acgfbd", "efgcab", "cgfeabd", "dbcg", "bg"},
            {"bc", "egdba", "ebdacg", "cgba", "dabfec", "bec", "defcg", "cdbefga", "gbfead", "becdg"},
            {"deagbfc", "agebdc", "fbedca", "fa", "edgafc", "bfac", "adcbe", "gfedb", "afd", "efdab"},
            {"afgdeb", "beagd", "daecbfg", "gbfedc", "ecd", "becad", "egac", "agecbd", "ce", "fbcda"},
            {"fgabec", "gefb", "cdagefb", "fbacgd", "dgecba", "adecf", "eg", "cefga", "egc", "gcfba"},
            {"gbcf", "fceag", "gb", "bag", "bdafge", "gbeac", "fdegca", "cfegdab", "egfacb", "daceb"},
            {"gbac", "fedagb", "gaf", "adcbf", "ceabfd", "abecgfd", "bfcdga", "ga", "edgcf", "gfcda"},
            {"gdbfc", "fbaecd", "gaef", "ef", "efb", "agebc", "gabdefc", "ebcgad", "facegb", "bgcfe"},
            {"fbacge", "decgba", "bcafd", "fedgab", "fageb", "ed", "efgd", "ebafd", "gedbcfa", "bed"},
            {"bcgdf", "badgec", "de", "fecgd", "cfeag", "dfbe", "dcgafeb", "gdacfb", "ced", "fdbgce"},
            {"gebfa", "fd", "dgacb", "dfgba", "bfdeca", "daf", "becfga", "edfg", "afgebcd", "gfadbe"},
            {"fgdcb", "efdcg", "gcfabd", "gcdbafe", "gbf", "acfb", "aegcdb", "edfabg", "fb", "gbcda"},
            {"de", "fadcg", "befcdg", "fbeac", "adcef", "efd", "ebgfca", "cbefadg", "edba", "dbaecf"},
            {"ebcfad", "fbecgd", "edgba", "df", "adgfcbe", "dfe", "bcfaeg", "bedgf", "bcefg", "dgfc"},
            {"acgdb", "gdc", "gcebdaf", "abcge", "ecgdfa", "egbd", "cbaegf", "gdacbe", "fcdba", "dg"},
            {"gc", "cge", "cgdb", "deafbgc", "dageb", "cbega", "agbcde", "cedgfa", "gbefad", "cefab"},
            {"abegc", "agecf", "adfcbge", "bcdaeg", "fbadec", "bca", "cb", "bcgd", "egdba", "gbafde"},
            {"daecgb", "abegd", "bagedf", "begfcd", "fe", "feag", "abefd", "bfe", "fbgceda", "fbdac"},
            {"eafcbdg", "abcdg", "acegf", "dfcga", "fgaebc", "defcbg", "aefd", "dfc", "dfgace", "df"},
            {"fdecabg", "ba", "agebdc", "adb", "gcdea", "befdg", "gabc", "egdba", "badefc", "ecagdf"},
            {"cdfg", "dbecf", "dg", "caebdf", "gbd", "beafg", "cbegfd", "ebcgdfa", "gdfeb", "agdecb"},
            {"bca", "cadbfeg", "gabfcd", "bcgafe", "caebg", "adgfec", "bc", "bcfe", "gefac", "beadg"},
            {"cabgdef", "cgabde", "efcab", "degcf", "gcfdeb", "da", "fgda", "cda", "feagcd", "aedcf"},
            {"dagfbe", "gaefb", "efcgadb", "fbcae", "fg", "gbf", "edbag", "cbgdae", "gadfbc", "gfde"},
            {"cbedag", "edgca", "bfcagde", "gfbae", "gfdea", "df", "adfc", "dafceg", "fedbcg", "gdf"},
            {"ebfc", "ef", "gecfad", "gfdcb", "egbad", "gfabcd", "ecgdafb", "feg", "bfgdce", "dbefg"},
            {"ce", "cefd", "cafdg", "cdgefa", "geacbd", "eca", "gcbfad", "gebfa", "afecg", "bfedcga"},
            {"ceb", "edbgafc", "fabec", "cb", "dfagec", "fgbea", "daefcb", "efdca", "cebgda", "bfdc"},
            {"cgf", "aefbdgc", "gf", "bagf", "gedacf", "ecgfb", "cdfeb", "acgbe", "ebcfga", "cedbga"}
    };

    static String[][] output = {
            {"bf", "efb", "bgecdfa", "egcfa"},
            {"bgcdef", "dgbec", "bfeac", "fdeg"},
            {"efbad", "fbdea", "dae", "fagbd"},
            {"decbaf", "cdfg", "deabg", "cd"},
            {"gf", "acegd", "fdeabgc", "gfd"},
            {"caegfbd", "dgcebfa", "eg", "bgdfae"},
            {"dbecga", "dbgcefa", "cfg", "fcg"},
            {"cfbg", "fabce", "bg", "fdgeacb"},
            {"geadbc", "bfgeacd", "efagcdb", "cefbg"},
            {"aecdgfb", "dbcfag", "cdaf", "gfecba"},
            {"fdegcb", "acbdf", "ed", "gdae"},
            {"geb", "gbe", "deag", "eg"},
            {"bdcegfa", "gbfce", "dbgecaf", "ac"},
            {"cb", "fgedc", "cb", "cdfeb"},
            {"gfcdbae", "fb", "abcefgd", "bef"},
            {"cfe", "caedfgb", "fc", "eadfgc"},
            {"abgd", "bdgcf", "ebcdfga", "fcedbga"},
            {"cdb", "cb", "dbc", "cdafbeg"},
            {"dcage", "bec", "cb", "cb"},
            {"dgebf", "dc", "bfgde", "cde"},
            {"fbdgaec", "ef", "ef", "cafbe"},
            {"fe", "gfe", "geabf", "dgecab"},
            {"egadc", "cfd", "efbdg", "gcbf"},
            {"agd", "gbdf", "bdafcge", "bcfegad"},
            {"gfabecd", "efdgbc", "fcebgd", "fbd"},
            {"ba", "bacgfde", "edfgac", "ab"},
            {"ebgfdc", "eagcb", "eafd", "bef"},
            {"aegbfc", "fcb", "cgfdbe", "fcaged"},
            {"abdegf", "gbad", "baecgf", "afdbeg"},
            {"bgd", "fcbgd", "gdb", "dbfa"},
            {"dcf", "cf", "gcedaf", "bgfc"},
            {"befga", "cbf", "gcfe", "bcf"},
            {"cdbgea", "gcb", "fdbg", "adgefcb"},
            {"gfb", "fbg", "dbgecfa", "bacg"},
            {"gb", "gbfd", "adgefc", "ebg"},
            {"cgaefbd", "dfaeg", "dafgb", "dfeagc"},
            {"fabe", "bafecd", "fecgdab", "ae"},
            {"cdbge", "efbgcda", "dagcbe", "agebdf"},
            {"deagc", "ec", "ebagd", "cafgeb"},
            {"cdf", "cf", "febadgc", "ecgfd"},
            {"cgebaf", "dacge", "fdbc", "decga"},
            {"cagbe", "bgfa", "gbced", "gabf"},
            {"eaf", "cdafb", "efa", "ae"},
            {"abcfd", "dgc", "cgd", "gdc"},
            {"gcad", "eca", "agbced", "gdbace"},
            {"bdefa", "cgebfad", "gdfc", "gd"},
            {"fcabge", "dbfagec", "gebdfca", "gbfcd"},
            {"bfdcega", "aeg", "bdgaf", "bedg"},
            {"cbgedfa", "ag", "egda", "ga"},
            {"bfe", "gfeabd", "gdbef", "dagfbec"},
            {"efcdgab", "edgbcf", "efabcg", "dgecb"},
            {"bae", "abcgef", "gbdcefa", "dbga"},
            {"bdface", "cgfabde", "cdf", "cdafeb"},
            {"gdfac", "cgdef", "cgfad", "cfbedga"},
            {"dbcafeg", "dfecag", "dbfc", "efgdacb"},
            {"fea", "cebfda", "dfceag", "fadcebg"},
            {"ecbad", "bfdec", "acd", "ac"},
            {"fcageb", "dgb", "dgfeb", "bfad"},
            {"fegc", "bcaeg", "beafc", "feadbc"},
            {"gbafedc", "daefg", "fd", "gbadec"},
            {"ea", "gdecb", "bfacge", "gbace"},
            {"febcg", "cfadeg", "ebcgda", "dbfa"},
            {"dgbf", "edabf", "bd", "dfgb"},
            {"efga", "ea", "cgafbed", "acfdb"},
            {"begcdfa", "ace", "fdegbac", "ac"},
            {"cafgdbe", "fbedagc", "adefbc", "gacefdb"},
            {"adf", "degf", "fad", "fedacgb"},
            {"eafdgb", "fdcge", "cafe", "dfe"},
            {"bega", "ace", "bacfg", "bgadcf"},
            {"cgadbef", "gba", "ag", "bag"},
            {"gfcbaed", "dbge", "dg", "acfbd"},
            {"edcf", "ef", "fe", "fe"},
            {"cf", "acgbd", "fdac", "cdbfega"},
            {"bgfae", "bec", "cfdebga", "cfegbd"},
            {"da", "afd", "baegdfc", "afd"},
            {"dgbaec", "gdbcfe", "dba", "ba"},
            {"dafg", "bdafec", "cbfeg", "facbg"},
            {"ac", "afgbce", "dacf", "fcda"},
            {"fbgae", "efgacb", "cadfeb", "bgdf"},
            {"dgabec", "cbdgae", "bcd", "aedcbgf"},
            {"cdabgf", "dbefgc", "bdgfa", "facgebd"},
            {"fbe", "ebfcdg", "bcfd", "fgcdabe"},
            {"fgd", "adcf", "bgfcd", "fdegba"},
            {"gdabfec", "bdgfc", "dcfba", "feab"},
            {"dcegbf", "fc", "becagd", "fc"},
            {"adbfecg", "acbge", "cabedfg", "gea"},
            {"gbd", "begdcf", "aegbd", "bgd"},
            {"ged", "abfgced", "gd", "afcde"},
            {"ecf", "ec", "ce", "efcdb"},
            {"eacdbg", "gbfdea", "begfd", "becfdag"},
            {"dfeb", "dfgabe", "adcgb", "de"},
            {"eadf", "adcegb", "efcbg", "cfabdg"},
            {"gbeaf", "cb", "bcad", "fadcge"},
            {"ec", "fbceagd", "fce", "fec"},
            {"bcedgf", "bedacgf", "cegfa", "bcfgead"},
            {"cfagbd", "dcega", "gaecdb", "bgfaecd"},
            {"ea", "eba", "deca", "bea"},
            {"beagcfd", "gdbcafe", "efac", "cf"},
            {"cagb", "cfagd", "gbd", "fegcbad"},
            {"gc", "gdeacf", "agdbce", "fbacdge"},
            {"eafbd", "abfged", "begf", "egfb"},
            {"ecf", "cagefbd", "befgdac", "acge"},
            {"adbcg", "fca", "agdbfce", "acf"},
            {"gedabfc", "eb", "eb", "aegcbfd"},
            {"fa", "eacgdbf", "fa", "defa"},
            {"gadefcb", "bfc", "bacfde", "bgedc"},
            {"fcdae", "dbgfec", "gfdea", "dfcgeba"},
            {"cbd", "geacdb", "adbcge", "dc"},
            {"gd", "gbd", "dabfc", "defg"},
            {"gd", "cfead", "dcfea", "cbefgda"},
            {"fc", "adfgb", "dgfab", "cgbedf"},
            {"bgdcfea", "fg", "bfdaegc", "afgcdbe"},
            {"efcdbg", "ae", "bdfce", "cdefa"},
            {"cgafdb", "fad", "fda", "acdg"},
            {"bacge", "egbacfd", "adgfc", "deg"},
            {"bc", "gadefb", "bc", "acbd"},
            {"cedfa", "egdcbfa", "fa", "afd"},
            {"ecbdgf", "df", "dabf", "fd"},
            {"defa", "ef", "ef", "fdae"},
            {"aecfg", "dfbacge", "cae", "ea"},
            {"bagfc", "gbfcdea", "gfdac", "bgda"},
            {"fbce", "cbg", "gcbfde", "gefabd"},
            {"bga", "gb", "gb", "ebfg"},
            {"fbcade", "cebfdag", "adc", "acd"},
            {"gfc", "egafd", "cf", "bcdeg"},
            {"beadg", "adefb", "fcbe", "dagfcb"},
            {"decaf", "bacdgf", "fgd", "ebgaf"},
            {"bcgfeda", "bdgf", "fg", "gf"},
            {"efgca", "efgca", "cef", "ebac"},
            {"afbcdg", "fa", "bdfgec", "dfbgae"},
            {"fb", "abfe", "aebf", "dgecbf"},
            {"badecf", "ed", "egfca", "edfb"},
            {"bafgdce", "gacbef", "ga", "dagb"},
            {"aefcg", "ae", "aegcbf", "ebga"},
            {"cg", "ecbad", "egdafbc", "cgd"},
            {"cdab", "fgceb", "ac", "fgbac"},
            {"bacef", "adbf", "bfda", "cab"},
            {"aegd", "ad", "da", "geabcfd"},
            {"dgc", "gdc", "cfedga", "cdg"},
            {"bcadfge", "edgacf", "bac", "dabgce"},
            {"bdfae", "cd", "eafdb", "cdf"},
            {"gbce", "ecadb", "daebcg", "gc"},
            {"bgeafdc", "feda", "edaf", "fbgac"},
            {"fadegcb", "gacfed", "defcbg", "dgcbaef"},
            {"dfcag", "gcedf", "cefabd", "fbadc"},
            {"bfdg", "fdbg", "ebagc", "beafg"},
            {"bagdf", "fa", "afg", "ebgcdf"},
            {"geadb", "be", "dceb", "ebdgca"},
            {"febcdg", "egdcfab", "dcebgf", "gef"},
            {"ecdbg", "abeg", "dfbeac", "fgdaceb"},
            {"aedbf", "ebdaf", "cda", "dca"},
            {"eab", "ecdgb", "bea", "ab"},
            {"gfe", "egf", "becafg", "abfgedc"},
            {"cbagfd", "fdbga", "dcbega", "dfgecba"},
            {"cfbdega", "dacb", "agdcfb", "gabfdc"},
            {"gf", "gf", "debafg", "fgb"},
            {"fcabd", "dfacgbe", "fcabedg", "ceadfg"},
            {"cedfa", "fb", "fb", "gabf"},
            {"adf", "ecad", "ebadf", "bdcgafe"},
            {"egabcdf", "cedga", "cedag", "adgf"},
            {"dfgc", "aebcg", "egdcfba", "agbec"},
            {"eafbgdc", "db", "db", "gdafcbe"},
            {"fgecba", "cgbfeda", "da", "dbecafg"},
            {"ebgad", "gfed", "debfcag", "fdge"},
            {"dbage", "bdgacef", "cfagdeb", "deabg"},
            {"fega", "fgae", "gafe", "cabde"},
            {"bcafedg", "ag", "ga", "adg"},
            {"dgcebaf", "bedf", "fb", "bcged"},
            {"fdc", "dbgafc", "cefbd", "cdf"},
            {"dcfaebg", "fg", "fgd", "fbgced"},
            {"ge", "gae", "age", "afebgdc"},
            {"fgdba", "bg", "gfacbed", "ecabgf"},
            {"bcefagd", "bec", "cebfgda", "cb"},
            {"fad", "dbaef", "cbfdae", "af"},
            {"fbadge", "eacdbg", "cde", "ce"},
            {"dafgbc", "gec", "bfeg", "gefb"},
            {"bg", "cagfeb", "aedbc", "cbaeg"},
            {"badcf", "fcedg", "adbcfg", "agebfd"},
            {"cfgdbae", "faeg", "efga", "cdegab"},
            {"de", "ed", "bdafe", "de"},
            {"de", "de", "edc", "gafbdc"},
            {"bdcfea", "bafecgd", "acdegfb", "fgbdea"},
            {"bgafde", "gfcbd", "fb", "cedbfga"},
            {"eagbfcd", "beagfc", "bcefgd", "beda"},
            {"df", "bcagef", "df", "aecfgb"},
            {"egcfda", "aebgfc", "aegcbdf", "cgd"},
            {"dabfeg", "gc", "eabcgdf", "gabde"},
            {"gbdc", "begad", "bfecda", "cb"},
            {"fbdac", "gcebdaf", "egfa", "fe"},
            {"df", "adef", "fd", "dfea"},
            {"bda", "gcabde", "gdaec", "fgdcae"},
            {"cedfb", "gd", "cbgeafd", "debgacf"},
            {"cgefab", "cgfae", "befc", "acbegf"},
            {"adc", "da", "cgaedf", "cgdfbe"},
            {"bgf", "gf", "acgdfb", "cfgdba"},
            {"adecg", "gbafe", "acdbfeg", "df"},
            {"aegdb", "bfged", "ef", "bafdgc"},
            {"ecgfa", "cea", "defc", "ce"},
            {"bec", "eadfgbc", "degabc", "adgfce"},
            {"gf", "fbgcade", "cbegfda", "acbdfeg"}
    };

    public static void part1() {
        int uniques = 0;

        for (String [] inputArray : output) {
            for (String digit : inputArray) {
                int length = digit.length();
                if (length == 2 || length == 3 || length == 4 || length == 7) uniques++;
            }
        }

        System.out.println("Count: " + uniques);
    }

    public static char getA(Map<Integer,Set<Character>> map) {
        // difference between 1 and 7 (2 and 3 chars)
        Set<Character> digit7 = new HashSet<>(map.get(7)); // use the copy constructor
        //intersection.retainAll(map.get(3)); // intersection
        digit7.removeIf(map.get(1)::contains); // difference between 7 and 1
        return digit7.stream().findFirst().get();
    }

    public static char getGAndPut9(Map<Integer,Set<Character>> map, char A, Set<String> digitsSet) {
        // get number 4 (4 chars)
        Set<Character> set = new HashSet<>(map.get(4)); // use the copy constructor
        set.add(A); // we add A to the 4 chars that 4 is made of
        Set<Character> set6Chars;

        // then we have to intersect against all the digits with 6 chars
        // the one that has only 1 char left will be the digit 9, and the char left will be G
        for (String digit : digitsSet) {
            int length = digit.length();
            if (length == 6) {
                set6Chars = digit.chars()
                        .mapToObj(chr -> (char) chr) // autoboxed to Character
                        .collect(Collectors.toSet());
                set6Chars.removeIf(set::contains); // difference between number (6 chars) and our set (4 + char A)
                if (set6Chars.size() == 1) {
                    map.put(9,digit.chars()
                            .mapToObj(chr -> (char) chr) // autoboxed to Character
                            .collect(Collectors.toSet()));
                    digitsSet.remove(digit); // 9 is removed from the set
                    return set6Chars.stream().findFirst().get();
                }
            }
        }
        System.out.println("ERROR: 9 has not been found!");
        return 'Z'; // We should never get here
    }

    public static char getDAndPut3(Map<Integer,Set<Character>> map, char G, Set<String> digitsSet) {
        // get number 7 (3 chars)
        Set<Character> set = new HashSet<>(map.get(7)); // use the copy constructor
        set.add(G); // we add G to the 3 chars that 7 is made of
        Set<Character> set5Chars;

        // then we have to make the difference against all the digits with 5 chars (2, 3 and 5)
        // the one that has only 1 char left will be the digit 3, and the char left will be D
        for (String digit : digitsSet) {
            int length = digit.length();
            if (length == 5) {
                set5Chars = digit.chars()
                        .mapToObj(chr -> (char) chr) // autoboxed to Character
                        .collect(Collectors.toSet());
                set5Chars.removeIf(set::contains); // difference between number (5 chars) and our set (3 chars + char G)
                if (set5Chars.size() == 1) {
                    map.put(3,digit.chars()
                            .mapToObj(chr -> (char) chr) // autoboxed to Character
                            .collect(Collectors.toSet())); // 3 is added to the map
                    digitsSet.remove(digit); // 3 is removed from the set
                    return set5Chars.stream().findFirst().get();
                }
            }
        }

        System.out.println("ERROR: 3 has not been found!");
        return 'Z'; // We should never get here
    }

    public static void put0(Map<Integer,Set<Character>> map, Set<String> digitsSet, char D) {
        // the only digit with 6 chars with no D char, is digit Zero
        Set<Character> set6Chars;

        for (String digit : digitsSet) {
            int length = digit.length();
            if (length == 6) {
                set6Chars = digit.chars()
                        .mapToObj(chr -> (char) chr) // autoboxed to Character
                        .collect(Collectors.toSet());
                if (!set6Chars.contains(D)) {
                    map.put(0,set6Chars); // 0 is added to the map
                    digitsSet.remove(digit); // 0 is removed from the set
                    return;
                }
            }
        }

        System.out.println("ERROR: 0 has not been found!");
    }

    public static void put6(Map<Integer,Set<Character>> map, Set<String> digitsSet) {
        Set<Character> set6Chars; // the only remaining number with 6 chars is 6

        for (String digit : digitsSet) {
            int length = digit.length();
            if (length == 6) {
                set6Chars = digit.chars()
                        .mapToObj(chr -> (char) chr) // autoboxed to Character
                        .collect(Collectors.toSet());
                map.put(6,set6Chars); // 6 is added to the map
                digitsSet.remove(digit); // 6 is removed from the set
                return;
            }
        }

        System.out.println("ERROR: 6 has not been found!");
    }

    public static void put5And2(Map<Integer,Set<Character>> map, Set<String> digitsSet) {
        // get number 6 (6 chars)
        Set<Character> set6 = new HashSet<>(map.get(6)); // use the copy constructor
        Set<Character> set5Chars; // can be 2 and 5

        // then we have to make the difference between the number with 5 chars (2 and 5) and number 6 (6 chars)
        // the one that has no chars left will be digit 5, and other one will be digit 2 (1 char left)
        String two = "";
        String five = "";
        for (String digit : digitsSet) {
            set5Chars = digit.chars()
                    .mapToObj(chr -> (char) chr) // autoboxed to Character
                    .collect(Collectors.toSet());
            set5Chars.removeIf(set6::contains); // difference between number (5 chars) and set with chars from number 6
            if (set5Chars.size() == 0) {
                map.put(5,digit.chars()
                        .mapToObj(chr -> (char) chr) // autoboxed to Character
                        .collect(Collectors.toSet())); // 5 is added to the map
                five = digit;
            } else { // set5Chars.size() == 1
                map.put(2,digit.chars()
                        .mapToObj(chr -> (char) chr) // autoboxed to Character
                        .collect(Collectors.toSet())); // 2 is added to the map
                two = digit;
            }
        }

        digitsSet.remove(two); // 2 is removed from the set
        digitsSet.remove(five); // 5 is removed from the set

        if (digitsSet.size() != 0) System.out.println("ERROR: 5 or 2 have not been found!");
    }

    /**
     *
     *    aaa
     *   b   c
     *   b   c
     *    ddd
     *   e   f
     *   e   f
     *    ggg
     *
     */
    public static Map<Integer,Set<Character>> decode(String[] digitsArray) {
        // set to maintain the remaining digits not in the map
        Set<String> digitsSet = new HashSet<>();
        Collections.addAll(digitsSet,digitsArray);

        Set<Character> digitChars;
        // maps number of Chars with its chars for every String digit. Uniques only (1, 7, 4 and 8)
        Map<Integer,Set<Character>> map = new HashMap<>();

        for (String digit : digitsArray) {
            int length = digit.length();
            if (length == 2 || length == 3 || length == 4 || length == 7) {
                digitsSet.remove(digit);
                digitChars = digit.chars()
                        .mapToObj(chr -> (char) chr) // autoboxed to Character
                        .collect(Collectors.toSet());
                // 1, 7, 4 and 8
                switch (length) {
                    case 2: // number 1
                        map.put(1, digitChars);
                        break;
                    case 3: // number 7
                        map.put(7, digitChars);
                        break;
                    case 4: // number 4
                        map.put(4, digitChars);
                        break;
                    case 7: // number 8
                        map.put(8, digitChars);
                        break;
                }
            }
        }

        Character A = getA(map);
        System.out.println("A is: " + A);
        Character G = getGAndPut9(map, A, digitsSet); // now we have 9 in the map as well (1, 4, 7, 8 and 9)
        System.out.println("A: " + A + " and G: " + G);
        Character D = getDAndPut3(map, G, digitsSet);
        System.out.println("A: " + A + " and G: " + G + " and D: " + D);
        // right now, remaining numbers are 0, 2, 5 and 6
        put0(map, digitsSet, D);
        // remaining numbers are 2, 5 and 6
        put6(map, digitsSet);
        // remaining numbers are 2 and 5
        put5And2(map, digitsSet);

        // no remaining numbers
        return map;
    }

    public static String getOutputNumber(Map<Integer,Set<Character>> map, String[] outputArray) {
        String numberString = "";
        for (String digit : outputArray) {
            Set<Character> digitSet = digit.chars()
                    .mapToObj(chr -> (char) chr) // autoboxed to Character
                    .collect(Collectors.toSet());

            for (Map.Entry entry : map.entrySet()) {
                if (entry.getValue().equals(digitSet)) numberString += entry.getKey();
            }
        }

        return numberString;
    }

    public static void part2() {
        long sum = 0;

        for (int i = 0; i < digits.length; i++) {
            Map<Integer,Set<Character>> map = decode(digits[i]);
            String number = getOutputNumber(map, output[i]);
            sum += Integer.valueOf(number);
        }

        System.out.println("Total is: " + sum);
    }

    public static void main(String[] args) {
        //part1();
        part2();
    }

}
