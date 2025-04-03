Autorid: Kaur Kingsepp, Henri Sulbi

Tegime Sudoku mängu, mida saab konsoolis mängida. Mängu mängimiseks on vaja koondada kõik .java failid üheks projektiks ja jooksutada Main.java faili.
Mängitakse sudoku reegilite järgi. Kasutaja saab ise alguses valida, kui mitu ruutu kogu 9x9 ruudustikust täidetud on, et lahendama hakata (17 - 80 ruutu).
Peale seda genereeritakse ruudustik, mille jaoks kasutaja saab hakkata sisestama väärtusi. Väärtuse sisestamine käib kujul: X-koordinaat *space* Y-koordinaat *space* väärtus.
Kui kasutaja on sisestanud kõik ruudud ja lahendus on õige, siis mäng saab läbi ja programm lõpetab töö. Kui kõik on sisestatud ja lahendus ei ole õige, 
siis kestab mäng edasi. 

Programmis on 6 klassi. 
1. Main.java, kus lihtsalt lootakse mängu objekt ja kutsutakse tema meetodit PlayGame, et mängu mängida. 
2. SudokuMain.java. Siin on ainult üks meetod PlayGame, kus on kirjas kogu mängu tsükkel ja loodud selleks vajalikud objektid.
3. SudokuLaud.java. Siin on isendimuutuja SudokuRuut objektide maatriksiga. On meetodid laua täitmiseks (SudokuRuut objektidega) väärtusega 0, 
lauast koopia tegemine, get meetod laua ruutude saamiseks, Uue ruudu täitmiseks uue SudokuRuut objektiga, kui ta vastab järgmise meetodi nõuetele. 
get meetod laual tühjade ruutude saamiseks (väärtus 0). Ja lõpuks meetod, millega kontrollida, kas kogu Sudoku vastab nõuetele, et olla lahendatud. 
4. SudokuRuut.java. Siin on isendimuutujatega väärtus ja Isfixed klass, Isfixed tähendab seda, et seda ruutu muuta ei saa, need on esialgsed ruudud
mida meie programm kasutajale ette annab. Lisaks on get ja set meetodid.
5. ConsoleUI.java. Siin on 3 meetodit. Üks prindib Sudokulaua, teine küsib kasutajalt, paljude etteantud ruutudega ta mängida tahab ja kolmas on selleks, 
et kasutaja sisestaks sobival kujul koordinaadid ja ka 'q' vajutamisel mäng lõppeks. 
6. LauaGeneraator.java. Siin on genereeriTaielikLaud meetod, milles luseks võetakse korrektne sudoku ruudustik, sest selle ridu ja veergusi suvaliselt
vahetades on võimalikgenereerida täiesti teine, kuid ikkagi reeglitele vastav sudoku. kustutaRuudud meetod, mis kustutab suvaliselt sealt genereeritud
ruudustikust ruute. Peale ühe ruudu kustutamist kontrollitakse läbi leiaLahendus meetodi, ega Sudoku ei muutunud vahepeal lahendamatuks ja kas sellel on 
ikka üks lahendus. Kui neid reegleid rikuti, siis võtab sammu tagasi ja proovib muudmoodi (saab lähemalt uurida Sudoku backtracking). 

Kokkuvõttes koosneb SudokuLaud (maatriks) mitmetest SudokuRuut objektidest. SudokuRuut objektide väärtust saab muuta ehk muutub laual oleva ruudu väärtus. 
Kasutajale genereeritakse ühe lahendusega laud vastavalt sellele, mitu ruutu ta tahab alles jätta. 

Projektis oli tööjaotus võrdne. Kaur tegi alguses klassid 1 - 5 nii, et tühja Sudokulauda sai mängida ja kõik funktsionaalsused töötaksid. Henri tegi 
6. meetodi, mis oli projekti kõige ajamahukam ja keerulisem osa, ja täiendas ka vastavalt ülejäänud klasse, et kogu asi ettenähtud korras töötaks. 
Mõlemad kulutasime projekti tegemiseks umbes 15h. 

Edasi saaks arendada seda, et küsida kasutajalt raskusastet, selle asemel, et küsida, mitme ruuduga mängida tahad. Või kasvõi mõlemat küsida. 
Samuti saaks teha UI. 


