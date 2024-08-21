# Zadanie #

## Wymagania ##

Napisać aplikację imitującą działanie banku. Aplikacja będzie pozwalała na dodawanie
nowych klientów, zakładanie nowych rachunków, oraz wykonywanie przelewów.

## Domena  (5 pkt) ##

Bank przechowuję listę klientów (klasa `Client`), każdy klient ma imię, nazwisko, address (ulica, miasto, kod) jako osobna klasa `Address`, pesel oraz `clientID` - identyfikator w banku.
Każdy klient może mieć listę kont (klas rozszerzających klasę `Account`), konto ma swój nr, walutę, w jakiej jest prowadzone (`enum Currency`), może mieć nazwę, oraz aktualne saldo typu `BigDecimal` oraz prowizję (`fee`) jaka jest pobierana za transakcję (`BigDecimal`).

W banku jest kilka rodzajów kont - `BasicAccount` które ma prowizję równą `0.1`, `PremiumAccount`, które ma prowizję równą `0.5` oraz `FreeAccount`, które ma prowizję równą `0`.

Dodatkowo każde konto ma historię transakcji w postaci listy `Transaction`. Transakcja przechowuje informację o dacie(`java.util.Date`), kiedy została wykonana, stronach transakcji (nadawca, odbiorca) oraz kwocie transakcji z odpowiednim znakiem (dodatnią dla uznań i ujemną dla obciążeń). Każda transakcja ma również nr porządkowy-generowany automatycznie.

## Funkcjonalności ##

### Przechowywanie klientów i ich kont (5 pkt) ###

Klasa `ClientService` przechowuje listę klientów wraz z ich kontami. Klasa posiada metody do dodawania klientów do listy klientów. Klasa powinna również umożliwiać wyszukiwanie klientów po nazwisku i nr pesel oraz identyfikatorze.

### Dodawanie klientów  (5 pkt) ###

Dodawanie nowych klientów do banku zajmuje się klasa `ClientBuilder` która pozwala stworzyć klienta i dodać go do listy klientów banku. Klasa ta powinna sama generować kolejne identyfikatory klientów banku-można użyć statycznej zmiennej ew. klasy `java.util.UUID`. Klasa walidować dane klienta-np. nie pozwalać dodać klienta o takim samym nr pesel, czy pustych danych-w takim wypadku metoda powinna rzucić odpowiedni wyjątek, który będzie obsłużony w innej części aplikacji.

### Tworzenie kont (7 pkt) ###

Konta klientów powinny być tworzone poprzez klasę `AccountService`, która na podstawie typu konta i waluty w jakiej, ma być prowadzone stworzone konto i pozwoli je dodać do listy kont danego klienta. Klasa powinna obsłużyć sytuacje wyjątkowe, np próba założenia rachunku dla niesiniejącego klienta-w takim wypadku metoda powinna rzucić odpowiedni wyjątek, który będzie obsłużony w innej części aplikacji.

### Przelewy (8 pkt) ###

Aplikacje posiada serwis `TransferService` który pozwala wykonać przelew,
pomiędzy dwoma kontami na podaną kwotę. Metoda która, wykonuje przelew powinna stworzyć obiekt transakcji (w dowolny sposób-może być jakiś wzorzec projektowy, może być zwykły konstruktor) z ustawionymi odpowiednimi polami i powinna dodać transakcję do list transakcji obu klientów, oraz zmienić saldo obu klientów, jednocześnie uwzględniając prowizję za przelew. Próba wykonania przelewu na konta w różnych walutach powinna skutkować rzuceniem odpowiedniego wyjątku. Próba wykonania przelewu na kwotę większą niż saldo powinna rzucić odpowiedni wyjątek.


**Dla każego z serwisów powyżej należy dodać testy, każda klasa testów powinna testować metody z odpowiedniej klasy serwisu. Testy powinny weryfikować scenariusz poprawnego i niepoprawnego (gdy metoda rzuca wyjątek) działania aplikacji.**

### Interfejs WEB (10 pkt) ###
                  
Aplikacja banku powinna być aplikacją webową w SpringBoot (istniejąca klasa `BankApplication`). 

Aplikacja powinna pozwalać dodawać klientów, wyszukiwać klientów po przez odpowiednie metody HTTP (`GET`, `POST`) i odpowiednie parametry wejściowe-w zależności od metody HTTP. Np. wyszukiwanie klienta powinno być dostępne pod adresem  `/client&pesel=` jako metoda `GET` zgodnie z `REST API`, a dodawanie powinno być obsłużone jako metoda `POST` pod adresem `/client`.

Analogicznie jak dla klienta, aplikacja powinna wystawiać `REST API` dla tworzenia kont oraz przelewów. 

Dane wysyłane do i z aplikacji powinny być w formacie `JSON`. Obsługę wyjątków należy zapewnić poprzez odpowiednie statusy odpowiedzi i komunikaty błędów, np. `404` - np. jeśli wyszukanie nie zwróciło klienta, `503` - np. jeśli saldo rachunku jest mniejsze niż kwota przelewu. 

## Testowanie aplikacji ##

Do pliku `rest-api.http` dodać testowe wywołania metod HTTP, pozwalające na stworzenie klienta, stworzenie konta oraz wykonanie przelewu. 
              

## Zasady oceniania ##

* Kod musi się kompilować -tzn. aplikacja musi się uruchamiać.
* Stosujemy dobre praktyki, jeśli chodzi o nazwy klas, zmiennych itp.
* Zastosowane jest obiektowe podejście-odpowiednia hierarchia klas i pakiety, tam gdzie to możliwe i wskazane.
* Zastosowane `Stream API` do działaniach na kolekcjach.
* Testy sprawdzają działanie aplikacji dla scenariuszy negatywnych i pozytywnych, dodatkowo wszystkie testy przechodzą poprawnie.
* Aplikacja używa `REST API` - tzn odpowiednich metod `HTTP`, url i odpowiednich statusów odpowiedzi.
* Praca jest samodzielna-bez użycia generative AI (Copilot, Chat GPT, Claude, etc. ) 
