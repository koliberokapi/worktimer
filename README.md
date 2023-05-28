#                      WORKTIMER

Aplikacja - rejestrator czasu pracy nad projektami

## Funkcje:
- dodawanie nowych projektów i tasków
- rejestracja czasu startu dla poszczególnych tasków
- zatrzymanie czasu pracy przy tasku
- możliwość kontynuacji zadań i aktualizacja czasu pracy
- wypisanie projektów i tasków oraz czasów rozpoczęcia i zakończenia pracy nad nimi
- generowanie raportów końcowych czasu pracy nad poszczególnymi projektami i taskami


## Zasada działania


![](https://user-images.githubusercontent.com/108087334/241393094-2be08aa1-0e01-43b1-9dac-72a38072ee98.png)

<!--   ## Cechy: 
+ 8 klas
+ plik .csv dla logów
+ plik .jar 
+ .... -->

## Komendy 

| Komenda       | Opis                  |
| ------------- | ------------------------------ |
| `help`   | Wyświetla wszystkie opcje programu |
| `start`      | Tworzy nowy log do projektu i tasku, zapisuje czas startu pracy     |
| `stop`   | Zapisuje czas zakończenia pracy w logu |
| `continue` + param  | Kontynuuje zapisane zadanie zgodnie z liczbą wskazaną w parametrze (domyślnie ostanie) |
| `last` + param | Wyświetla ostatnie zadania zgodnie z liczbą wskazaną w parametrze (domyślnie 5) |
   | `list`   | Wypisuje projekty i taski wraz z czasami startu i zatrzymania     |
| `report`   | Generuje raport czasu pracy nad taskami i projektami  |
| `file`   | Zmiana źródła pliku  |


## Raporty
- dla przedziałów czasowych: 
   + raport dzienny
   + raport za ostatni miesiąc (ostatnie 30 dni)
   + raport za poprzedni miesiąc kalendarzowy

| PROJEKT  | TASK  | CZAS PRACY |
| :------------ |:---------------:| -----:|
| PROJEKT      |  | 5h 20min |
|      | Zadanie 1        |  1h 20 min |
|  | Zadanie 2      |    2h |
|  | Zadanie 3      |    1h 30min |
|  | Zadanie 4      |   30 min |
| PROGRAM      |  | 1h 30min |
|      | Opis       |  1h   |
|  | Wygląd     |    30min |


## Lista wywołań

| Nazwa funkcji | Wywołanie funkcji                    |
| ------------- | ------------------------------ |
| `start`      | java -jar worktimer-1.0.jar start -p "mwo" -t "task1"     |
| `stop`   | java -jar worktimer-1.0.jar stop    |
| `continue` |  continue  |
| `last` |  last 6  |
| `list`   |  list --today  |
| `file`   | -f "newFile.csv"     |
| `help`   |  -help |

