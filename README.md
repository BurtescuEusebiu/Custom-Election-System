Sistem de Alegeri
Acest proiect modelează un sistem de alegeri care permite gestionarea votanților, candidaților, zonelor de vot, regiunilor și analizarea rezultatelor alegerilor.
Simulează procesul de votare, colectarea voturilor și analiza rezultatelor pe diferite niveluri (zonă, regiune și național).
De asemenea, sistemul urmărește activitățile frauduloase în cadrul procesului electoral.

Funcționalități
Votanți: Adăugarea și urmărirea votanților în diferite zone. Fiecare votant poate exprima un vot pentru un candidat.
Candidați: Înregistrarea candidaților și urmărirea voturilor pe care le primesc.
Zone: Organizarea zonelor (de exemplu, localități sau districte) în care se exprimă voturile, fiecare cu lista proprie de votanți și numărul de voturi.
Regiuni: Gruparea mai multor zone într-o regiune, cu agregarea datelor de vot la nivel regional.
Analiza Alegerilor: Analiza rezultatelor la nivel de zonă, regiune sau național, afișând numărul de voturi și procentele de distribuție a acestora.
Detectarea Fraudelor: Urmărirea și listarea activităților frauduloase în cadrul alegerilor.

Prezentare Generală a Claselor:

Candidate
Reprezintă un candidat în cadrul alegerilor.

Atribute:
CNP: Un identificator unic al candidatului.
age: Vârsta candidatului.
name: Numele candidatului.

Metode:
Getteri și setteri pentru fiecare atribut.


Voter
Reprezintă un votant eligibil pentru a vota în cadrul alegerilor.

Atribute:
CNP: Un identificator unic pentru votant.
age: Vârsta votantului.
graceless: O valoare booleană care indică dacă votantul este "neîndemânatic".
voted: O valoare booleană care indică dacă votantul a votat deja.
name: Numele votantului.

Metode:
vote(): Marchează votantul ca având dreptul de a vota.


Zone
Reprezintă o circumscripție (de exemplu, o localitate sau un district) în care votanții își exprimă voturile.

Atribute:
name: Numele zonei.
totalVotes: Numărul total de voturi exprimate în această zonă.
region: Regiunea din care face parte zona.
voters: O mapă cu votanți, identificată prin CNP.
votes: O mapă cu candidați și numărul de voturi pe care le-au primit în această zonă.

Metode:
addVoter(): Adaugă un votant în zonă.
addVote(): Înregistrează un vot pentru un candidat din partea unui votant.
freeVoters(): Șterge lista de votanți din zonă.
freeVotes(): Șterge numărul de voturi pentru toți candidații din zonă.
checkVoter(): Verifică dacă un votant există în zonă.
listVoters(): Listează toți votanții din zonă.
returnVotes(): Returnează lista de voturi sortată în funcție de candidat.


Region
Reprezintă o regiune (un grup de zone).

Atribute:
nrVotes: Numărul total de voturi din regiune.
votes: O mapă cu candidați și numărul de voturi primite în regiune.

Metode:
Getteri și setteri pentru fiecare atribut.


Election
Reprezintă alegerile propriu-zise, gestionând candidații, zonele și procesul electoral.

Atribute:
id: Un identificator unic pentru alegeri.
name: Numele alegerii.
status: Starea actuală a alegerii (de exemplu, neîncepută, în desfășurare sau încheiată).
candidates: O mapă cu candidați, identificată prin CNP-ul lor.
zones: O mapă cu zonele, identificate prin numele lor.
frauds: O stivă pentru a urmări activitățile frauduloase în cadrul alegerilor.

Metode:
Metode pentru a adăuga, șterge și verifica candidați, votanți și zone.
Metode pentru a lista candidații și voturile.
Metode pentru a gestiona starea alegerilor (începere, încheiere).
Metode de detectare a fraudelor pentru a adăuga și lista fraudele.


Analysis
Oferă uneltele de analiză pentru a evalua rezultatele alegerilor.

Metode:
analyzeZone(): Analizează rezultatele pentru o zonă specifică și afișează numărul de voturi și procentele.
analyzeNation(): Analizează rezultatele pentru întreaga națiune, agregând datele din toate regiunile.

Main:
Programul citește datele de la tastatură și utilizează o structură switch pentru a verifica toate cazurile posibile, 
inclusiv cazul implicit (default), care gestionează situațiile în care comanda introdusă este invalidă sau necunoscută.
Pentru a asigura o mai bună lizibilitate, implementarea comenzilor a fost realizată în funcții separate.

OBSERVAȚII:
Ar fi fost posibilă implementarea clasei Region astfel încât să conțină toate circumscripțiile (zonele).
Totuși, utilizarea regiunii este minimă, ceea ce înseamnă că această modificare nu este necesară.

Explicație:
Deși ar fi putut fi creată o structură mai complexă, în care fiecare regiune să conțină toate zonele corespunzătoare,
acest lucru nu adaugă un beneficiu semnificativ în contextul actual al aplicației.
În prezent, regiunile sunt doar grupări logice ale zonelor, iar analiza alegerilor se face în principal pe zone, nu pe regiunile ca entități independente.
Astfel, această abordare simplă este suficientă și nu impune modificări majore, menținând codul ușor de gestionat și eficient.

Utilizarea Inteligenței Artificiale (AI) a fost extrem de utilă în mai multe aspecte ale dezvoltării acestui proiect,
dar este important de menționat că AI-ul a contribuit doar într-o mică măsură la implementare, estimându-se că sub 30% din cod a fost generat sau ajutat de AI,
în timp ce restul de peste 70% a fost realizat individual.

AI-ul a ajutat semnificativ la generarea rapidă a metodelor de tip getter și setter pentru fiecare atribut din clasele utilizate.
Aceste metode sunt esențiale pentru manipularea obiectelor și au fost generate automat, economisind timp și reducând riscul de erori.
De asemenea, prin autocompletarea codului, AI-ul a sugerat sau completat automat fragmente de cod standard, cum ar fi construcția constructorilor sau definirea unor metode de acces.

AI-ul a fost de ajutor și în procesul de învățare a unor noi clase sau tehnici, de exemplu sortarea unui HashMap.
În loc să trebuiască să implementez manual logica de sortare, am învățat rapid cum să transform un HashMap într-un TreeMap pentru a obține ordonarea cheilor sau valorilor.
Acest tip de abordare este eficientă pentru gestionarea datelor structurate, iar AI-ul a ajutat înțelegerea modului de utilizare a acestora
În concluzie, AI-ul a fost un instrument de ajutor, însă marea majoritate a muncii a fost realizată manual, prin înțelegerea cerințelor și aplicarea logicii de programare.

Bonus:
În această aplicație, a fost tratat și cazul în care comanda citită este invalidă sau necunoscută.
De asemenea, ar fi fost posibil să se verifice situațiile în care argumentele unei comenzi au fost scrise necorespunzător.
Un alt caz limită relevant ar fi cel legat de introducerea CNP-ului, unde se pot realiza verificări suplimentare.
De exemplu, primele cifre, care reprezintă datele personale (cum ar fi data nașterii sau reședința), 
pot fi utilizate pentru a confirma criterii specifice, precum verificarea unei vârste minime de 18 ani.

Din punct de vedere al refactorizării comenzilor și al răspunsurilor din aplicație, ar putea fi implementate câteva modificări pentru îmbunătățirea funcționalității. 
De exemplu, s-ar putea adăuga posibilitatea de a lista toate comenzile disponibile împreună cu argumentele asociate acestora.
O altă îmbunătățire ar fi introducerea opțiunii de a gestiona votanții din străinătate, incluzând și o analiză detaliată a voturilor provenite din diaspora.
În cazul în care se detectează o posibilă fraudă, în loc să fie tratată automat ca atare, ar putea fi afișată o eroare care să solicite o confirmare suplimentară, 
pentru a determina dacă este într-adevăr o fraudă sau doar o greșeală de input.
De asemenea, atributul „neîndemânatic” ar putea deveni irelevant, deoarece implică utilizarea unor informații dificil de obținut și, prin urmare, nepractice.





