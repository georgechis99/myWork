//Avand numele firmei tale ales, vrei acum sa iti alegi locul unde vei construi cladirea. Tu primesti harta orasului
// care este o matrice de forma dreptunghiulara de dimensiuni N x M (N linii, M coloane, indexate de la 0). Fiecare
// celula a hartii va avea asociat un numar intreg reprezentand costul de a inchira acel loc. Daca numarul este negativ,
// asta inseamna ca acea celula NU poate fii inchiriata. Vrei ca sediul sa fie aproape de tine, care te afli in celula (X, Y).
// Esti dispus sa te deplasezi maxim K celule pentru a-ti alege locul.
//
//
//Cerinta:
//Sa se afiseze valoarea celei mai ieftine celule la distanta maxim K de locul unde te afli.
//
//Input:
//Pe prima linie se afla doua valori naturale separate de un spatiu, N si M, care reprezinta dimensiunile hartii primite.
//Pe a doua linie se afla 3 valori naturale despartite printr-un spatiu, X, Y si K, care reprezinta linia si coloana
// unde te afli, respectiv distanta maxima pe care esti dispus sa te deplasezi.
//Pe urmatoarele N linii se vor afla cate M numere intregi despartite prin spatiu reprezentand costul inchirierii acelei
// celule (numar negativ inseamna ca celula nu poate fi inchiriata).
//
//Output:
//Sa se afiseze un singur numar natural, reprezentand valoarea celei mai ieftine celule la distanta maxima K de locul
// unde te afli.
//
//
//Restrictions:
//4<= N, M <= 300
//0<= X < N
//0<= Y < M
//1<= K <= min(N, M)
//harta este indexata de la 0
//distanta dintre doua celule este distanta manhattan dintre coordonatele celulelor, formal:
// d( (x1,y1), (x2,y2) ) = |x1-x2| + |y1-y2|, unde |x| este valoare absoluta a lui x

package com.timbuchalka;

import java.util.Scanner;

class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int nr = 1;
        int n = scanner.nextInt();
        scanner.nextLine();
        int[] friends = new int[n];
        for (int i = 0; i < n; i++)
            friends[i] = scanner.nextInt();

        int max = 0;
        for (int i = 0; i < n; i++) {
            if (friends[i] > max)
                max = friends[i];
        }


        for (int i = 2; i < Math.sqrt(max); i++) {
            boolean check = true;
            for (int j = 0; j < n; j++) {
                if (friends[j] % i != 0)
                    check = false;
            }
            if (check)
                nr++;
        }
        System.out.println(nr);
    }
}
