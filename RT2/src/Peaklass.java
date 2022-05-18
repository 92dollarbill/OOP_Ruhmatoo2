import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Peaklass {
    public static void main(String[] args) throws Exception {

        Sõnastik dict = new Sõnastik("Terminiid.txt");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kas tahad sõna leida (L), või kirjutada fail valitud sõnadest ja nende tahendustest? (K)");
        String inputValik = scanner.next();
        if(inputValik.equals("L")){
            System.out.println("Sisesta sõna:");
            String sõnaLeida = scanner.next();
            System.out.println(dict.leia(sõnaLeida));
        }
        if(inputValik.equals("K")){
            System.out.println("Sisesta sõnad mis tahad faili kirjutada, eralda semikoloniga(;):");
            String inputValik2 = scanner.next();
            System.out.println(inputValik2);
            String[] inputValik3 = inputValik2.split(";");
            dict.kirjuta(inputValik3);
        }
        if(!inputValik.equals("L")&&!inputValik.equals("K")){
            throw new ID1OTErind("Jargi juhised palun, sisesta K voi L.");
        }
    }

}

