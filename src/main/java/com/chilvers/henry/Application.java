package com.chilvers.henry;

import org.kohsuke.args4j.*;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.*;
import java.util.stream.LongStream;

public class Application {
    private static final Map<String, String> elements = new HashMap<>();

    @Option(name = "-min", required = true, usage = "minimum number of elements to use")
    private int minNumberOfElementsToUse;

    @Option(name = "-max", required = true, usage = "maximum number of elements to use (for performance reasons, probably no more than 7 is recommended)")
    private int maxNumberOfElementsToUse;

    static {
        elements.put("Hydrogen", "H");
        elements.put("Helium", "He");
        elements.put("Lithium", "Li");
        elements.put("Beryllium", "Be");
        elements.put("Boron", "B");
        elements.put("Carbon", "C");
        elements.put("Nitrogen", "N");
        elements.put("Oxygen", "O");
        elements.put("Fluorine", "F");
        elements.put("Neon", "Ne");
        elements.put("Sodium", "Na");
        //elements.put("Magnesium", "Mg");
        elements.put("Aluminum", "Al");
        elements.put("Silicon", "Si");
        elements.put("Phosphorus", "P");
        elements.put("Sulfur", "S");
        elements.put("Chlorine", "Cl");
        elements.put("Argon", "Ar");
        elements.put("Potassium", "K");
        elements.put("Calcium", "Ca");
        elements.put("Scandium", "Sc");
        elements.put("Titanium", "Ti");
        elements.put("Vanadium", "V");
        elements.put("Chromium", "Cr");
//        elements.put("Manganese", "Mn");
        elements.put("Iron", "Fe");
        elements.put("Cobalt", "Co");
        elements.put("Nickel", "Ni");
        elements.put("Copper", "Cu");
//        elements.put("Zinc", "Zn");
        elements.put("Gallium", "Ga");
        elements.put("Germanium", "Ge");
        elements.put("Arsenic", "As");
        elements.put("Selenium", "Se");
        elements.put("Bromine", "Br");
        elements.put("Krypton", "Kr");
//        elements.put("Rubidium", "Rb");
//        elements.put("Strontium", "Sr");
        elements.put("Yttrium", "Y");
//        elements.put("Zirconium", "Zr");
//        elements.put("Niobium", "Nb");
        elements.put("Molybdenum", "Mo");
//        elements.put("Technetium", "Tc");
        elements.put("Ruthenium", "Ru");
//        elements.put("Rhodium", "Rh");
//        elements.put("Palladium", "Pd");
        elements.put("Silver", "Ag");
//        elements.put("Cadmium", "Cd");
        elements.put("Indium", "In");
//        elements.put("Tin", "Sn");
//        elements.put("Antimony", "Sb");
        elements.put("Tellurium", "Te");
        elements.put("Iodine", "I");
        elements.put("Xenon", "Xe");
//        elements.put("Cesium", "Cs");
        elements.put("Barium", "Ba");
        elements.put("Lanthanum", "La");
        elements.put("Cerium", "Ce");
        elements.put("Praseodymium", "Pr");
        elements.put("Neodymium", "Nd");
//        elements.put("Promethium", "Pm");
//        elements.put("Samarium", "Sm");
        elements.put("Europium", "Eu");
//        elements.put("Gadolinium", "Gd");
//        elements.put("Terbium", "Tb");
//        elements.put("Dysprosium", "Dy");
        elements.put("Holmium", "Ho");
        elements.put("Erbium", "Er");
//        elements.put("Thulium", "Tm");
//        elements.put("Ytterbium", "Yb");
        elements.put("Lutetium", "Lu");
//        elements.put("Hafnium", "Hf");
        elements.put("Tantalum", "Ta");
        elements.put("Tungsten", "W");
        elements.put("Rhenium", "Re");
        elements.put("Osmium", "Os");
        elements.put("Iridium", "Ir");
        elements.put("Platinum", "Pt");
        elements.put("Gold", "Au");
//        elements.put("Mercury", "Hg");
//        elements.put("Thallium", "Tl");
//        elements.put("Lead", "Pb");
        elements.put("Bismuth", "Bi");
        elements.put("Polonium", "Po");
        elements.put("Astatine", "At");
        elements.put("Radon", "Rn");
        elements.put("Francium", "Fr");
        elements.put("Radium", "Ra");
        elements.put("Actinium", "Ac");
        elements.put("Thorium", "Th");
        elements.put("Protactinium", "Pa");
        elements.put("Uranium", "U");
//        elements.put("Neptunium", "Np");
        elements.put("Plutonium", "Pu");
        elements.put("Americium", "Am");
//        elements.put("Curium", "Cm");
//        elements.put("Berkelium", "Bk");
//        elements.put("Californium", "Cf");
        elements.put("Einsteinium", "Es");
//        elements.put("Fermium", "Fm");
//        elements.put("Mendelevium", "Md");
        elements.put("Nobelium", "No");
//        elements.put("Lawrencium", "Lr");
//        elements.put("Rutherfordium", "Rf");
//        elements.put("Dubnium", "Db");
//        elements.put("Seaborgium", "Sg");
        elements.put("Bohrium", "Bh");
//        elements.put("Hassium", "Hs");
//        elements.put("Meitnerium", "Mt");
        elements.put("Darmstadtium", "Ds");
//        elements.put("Roentgenium", "Rg");
//        elements.put("Copernicium", "Cn");
//        elements.put("Nihonium", "Nh");
        elements.put("Flerovium", "Fl");
        elements.put("Moscovium", "Mc");
//        elements.put("Livermorium", "Lv");
        elements.put("Tennessine", "Ts");
        elements.put("Oganesson", "Og");
    }

    public static void main(String[] args) throws IOException {
        new Application().doMain(args);
    }

    private void doMain(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        StopWatch stopWatch = new StopWatch("Creating Elemental Words");
        List<String> items = new ArrayList<>(elements.values());
        final Integer[] totalWords = {0};

        try {
            parser.parseArgument(args);

            for (int i = minNumberOfElementsToUse; i <= maxNumberOfElementsToUse; i++) {
                stopWatch.start("getting subsets of " + i);
                System.out.println("Words with " + i + " elements");
                System.out.println("-------------------------------");
                List<Set<String>> results = Subsets.getSubsets(items, i);

                for (Set<String> result : results) {
                    //System.out.println(result);

                    long permutations = Permutations.factorial(result.size());
                    //System.out.println(result + " can be combined in " + permutations + " different ways:");

                    LongStream
                            .range(0, permutations)
                            .forEachOrdered(j -> {
                                        String word = String.join("", Permutations.permutation(j, new ArrayList<>(result)));

                                        if (SpellChecker.isWord(word)) {
                                            totalWords[0]++;
                                            System.out.println(word);
                                        }
                                        //System.out.println(String.join("", Permutations.permutation(j, new ArrayList<>(result))))
                                    }
                            );

                    System.out.println();
                }

                System.out.println();
                stopWatch.stop();
            }

            System.out.println("Total words: " + totalWords[0]);
            System.out.println(stopWatch.prettyPrint());

            System.out.println("Press enter to quit...");
            System.in.read();
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java elementalwords [options...] arguments...");

            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();

            // print option sample. This is useful some time
            System.err.println("  Example: java elementalwords" + parser.printExample(OptionHandlerFilter.ALL));
        }
    }
}
