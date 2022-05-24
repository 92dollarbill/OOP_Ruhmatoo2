package com.example.ryhmatoo2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Sõnastik {
    String failinimi;
    Map<String, String> sõnavara= new HashMap<>();

    public Sõnastik(String failinimi) throws Exception {
        this.failinimi = failinimi;
        try (InputStream baidid = new FileInputStream(failinimi);
             InputStreamReader tekst = new InputStreamReader(baidid);
             BufferedReader puhverdatud = new BufferedReader(tekst)) {
            String rida = puhverdatud.readLine();
            while (rida != null) {
                System.out.println(rida);
                String[] sõnad = rida.split(";");
                sõnavara.put(sõnad[0], sõnad[1]);
                rida = puhverdatud.readLine();
            }
        }
    }

    String leia(String sõna) throws Exception{
        if (sõna.equals("")) {
            throw new TühiVäliErind("Sisestasite tühja välja.");
        }
        if (!sõnavara.containsKey(sõna)) {
            throw new SõnaEiLeidnudErind("Sõnastikus ei ole sõna: " + sõna);
        }

        return this.sõnavara.get(sõna);
    }

    void kirjuta (String[] sMassiiv) throws Exception {
        if (sMassiiv.length == 1 && sMassiiv[0].equals("")) {
            throw new TühiVäliErind("Sisestasite tühja välja.");
        }
        try (OutputStream baidid = new FileOutputStream("src/main/valitud.txt");
             OutputStreamWriter tekst = new OutputStreamWriter(baidid);) {
            for (String s : sMassiiv) {
                if (!sõnavara.containsKey(s)) {
                    throw new SõnaEiLeidnudErind("Sõnastikus ei ole sõna: " + s);
                }
                tekst.write(s + ";" + sõnavara.get(s) + "\n");
            }
        }
    }

}
