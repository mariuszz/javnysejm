JavnySejm - Java API dla [danych serwisu Sejmometr](http://sejmometr.pl)
============================================
Biblioteka pozwala w prosty sposób pobierać dane udostępniane przez serwis [sejmometr.pl](http://sejmometr.pl).

Zbudowany pakiet wraz z wszystkimi zależnościami można znaleźć pod adresem [http://download.porannajava.pl/javnysejm/latest](http://download.porannajava.pl/javnysejm/latest).

Dostępna jest również, stale rozbudowywana, [dokumentacja w formacie JavaDoc](http://download.porannajava.pl/javnysejm/latest/docs/api/).

Zgodność z API potwierdza oraz najlepszą dokumentację stanowi **kilkadziesiąt testów integracyjnych**

Licencja
========
Kod źródłowy biblioteki dostępny jest na licencji **Apache w wersji 2.0** dostępnej pod adresem: [http://www.apache.org/licenses/LICENSE-2.0.html](http://www.apache.org/licenses/LICENSE-2.0.html)
Warunki korzystania z danych przez nią udostępnianych są takie same jak dla danych pobieranych przez API serwisu Sejmometr i opisane są pod adresem [http://sejmometr.pl/api](http://sejmometr.pl/api)

Przykłady użycia
================

Założeniem biblioteki było jak najwierniejsze odwzorowanie oryginalnego API [sejmometr.pl](http://sejmometr.pl).

W związku z tym, dostęp do danych podzielony jest na dwa etapy:
*  pobranie identyfikatorów
*  pobranie obiektów

Do pobierania identyfikatorów służy *enum* **Ids**.
Poniższy przykład pokazuje jak pobrać identyfikatory klubów:
```java
int[] clubIds = Ids.CLUB.getIds();
```

Drugim etapem jest pobranie danych klubów. Jest to również bardzo proste:
```java
for (int i: clubIds)
{
	System.out.println(new Club.Builder(i).build());
}
```
Powyższy kod wyświetli następujący rezultat:

	Club{id=1, name=Platforma Obywatelska, shortName=PO}
	Club{id=2, name=Prawo i Sprawiedliwość, shortName=PiS}
	Club{id=3, name=Polskie Stronnictwo Ludowe, shortName=PSL}
	Club{id=4, name=Sojusz Lewicy Demokratycznej, shortName=SLD}
	Club{id=5, name=Ruch Palikota, shortName=RP}
	Club{id=6, name=Solidarna Polska, shortName=SP}
	Club{id=7, name=Niezrzeszeni, shortName=Niezrzeszeni}


**JavnySejm** został napisany z troską o możliwie jak najrzadsze odwoływanie się do serwera sejmometru.
W tym celu, intensywnie wykorzystuje [Builder pattern](http://en.wikipedia.org/wiki/Builder_pattern).

Najlepiej pokaże to poniższy przykład.

Najpierw pobierzmy posła o wdzięcznym identyfikatorze *13*:

```java
Deputy deputy = new Deputy.Builder(13).build();
```

Pobrane dane wyglądają następująco:


	Deputy{id=13, code=marek-ast, fullName=Marek Ast, firstName=Marek, lastName=Ast, birthDate=1958-09-27, birthPlace=Zielona Góra, clubId=2,
	electoralDistrict=8, votesNumber=12598, profession=radca prawny, speeches=[], votings=[], deputyCommittees=[], deputyFinancialStatements=[], deputyFinancialBenefitsReports=[]}


Jeżeli poza podstawowymi danymi chcemy pobrać również informacje o komisjach, w których pracuje poseł, wykonujemy następujący kod:

```java
Deputy deputy = new Deputy.Builder(13).withDeputyCommittees().build();
```

Wynik to:


	Deputy{id=13, code=marek-ast, fullName=Marek Ast, firstName=Marek, lastName=Ast, birthDate=1958-09-27, birthPlace=Zielona Góra, clubId=2,
	electoralDistrict=8, votesNumber=12598, profession=radca prawny, speeches=[], votings=[],
	deputyCommittees=[DeputyCommittee{committeeId=18, interval=[2012-01-13‥+∞), function=},
	DeputyCommittee{committeeId=8, interval=[2011-11-17‥+∞), function=zastępca przewodniczącego},
	DeputyCommittee{committeeId=30, interval=[2011-11-17‥+∞), function=}], deputyFinancialStatements=[], deputyFinancialBenefitsReports=[]}


Jeśli chcemy dowiedzieć się czegoś więcej o komisjach posła, musimy wykonać następujący kod:

```java
Deputy deputy = new Deputy.Builder(13).withDeputyCommittees().build();
for (DeputyCommittee deputyCommittee: deputy.getDeputyCommittees())
{
	System.out.println(new Committee.Builder(deputyCommittee.getCommitteeId()).build());
}
```

W ten sposób, otrzymamy następującą listę:


	Committee{id=18, name=Komisja Nadzwyczajna do spraw zmian w kodyfikacjach, code=NKK, contact=tel.: (22) 694-21-30, 694-18-26}
	Committee{id=8, name=Komisja Mniejszości Narodowych i Etnicznych, code=MNE, contact=tel.: (22) 694-18-29, 694-20-48}
	Committee{id=30, name=Komisja Sprawiedliwości i Praw Człowieka, code=SPC, contact=tel.: (22) 694-23-93, 694-24-44}



Analogicznie możemy uzyskać dostęp do pozostałych danych udostępnianych przez Sejmometr.

Po więcej informacji zapraszam do [dokumentacji w formacie JavaDoc](http://download.porannajava.pl/javnysejm/latest/docs/api/) oraz testów integracyjnych.

*dobrej zabawy ...*