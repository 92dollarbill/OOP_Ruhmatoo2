import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Sõnastik {
    String failinimi;
    Map<String, String> sõnavara= new HashMap<>();

    public Sõnastik(String failinimi) throws Exception {
        this.failinimi = failinimi;
        try (
                InputStream baidid = new FileInputStream(failinimi);
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
        if (Pattern.matches(".*\\p{InCyrillic}.*", sõna)){
            throw new KüsiSvetlanaKäestErind("See vist vene keeles, küsi Svetlana käest");
        }

        if (!sõnavara.containsKey(sõna))
        {
            throw new SõnaEiLeidnudErind("Sõna sõnastikus pole");
        }

        return this.sõnavara.get(sõna);
    }
    void kirjuta (String[] sMassiiv) throws Exception{
        try (OutputStream baidid = new FileOutputStream("valitud.txt");
             OutputStreamWriter tekst = new OutputStreamWriter(baidid);){
            for (String s : sMassiiv) {
                tekst.write(s + ";" + sõnavara.get(s) + "\n");

            }
        }


    }

}
