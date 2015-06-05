# Komendy
## LOGIN
Loguje użytkownika
```
> LOGIN <username>
< OK
```
* `<username>` - nazwa użytkownika

**Przykład:**
```
> LOGIN regzand
< OK
```
## INFO
Zwraca podstawowe informacje na temat danej rundy
```
> INFO
< OK <width> <height> <view distance> <money>
```
* `<width>` - szerokość mapy
* `<height>` - wysokość mapy
* `<view distance>` - zasięg działania komendy SCAN
* `<money>` - pieniądze użytkownika

**Przykład:**
```
> INFO
< OK 19 34 5 1320
```
## WAIT
Informuje o zakończeniu aktualnej tury
```
> WAIT
< OK
< OK <TURN or ROUND> 
```
* `<TURN or ROUND>` - TURN oznacza zakończenie tury, ROUNd zakończenie rundy

**Przykład:**
```
> WAIT
< OK
< OK TURN
```
## GET_TANKS
Zwraca ID czołgów urzytkownika
```
> GET_TANKS
< OK <count> <id> <id> <id> <id> ...
```
* `<count>` - ilość czołgów w posiadaniu urzytkownika
* `<id>` - numer identyfikacyjny czołgu

**Przykład:**
```
> GET_TANKS
< OK 6 23 5 2 9 11 32
```
## GET_TANK
Zwraca informacje o czołgu oraz amunicji która jest na jego pokładzie
```
> GET_TANK <tank id>
< OK <x> <y> <tank type id> <life> <count>
  <ammo type id> <amount>
  <ammo type id> <amount>
  <ammo type id> <amount>
  ...
```
* `<tank id>` - id czołgu o który pytamy
* `<x>`,`<y>` - pozycja czołgu
* `<tank type id>` - numer typu czołgu
* `<life>` - punkty zdrowia czołgu
* `<count>` - ilość typów amunicji znajdującej się w czołgu
* `<ammo type id>` - numer typu amunicji
* `<amount>` - ilość danej amunicji

**Przykład:**
```
> GET_TANK 21
< OK 3 41 5 67 2
  2 132
  5 11
```
## SCAN
Zwraca informacje o polach w pobliżu czołgu
```
> SCAN <tank id>
< OK <count>
  <x> <y> <type>
  <x> <y> <type>
  <x> <y> <type>
  ...
```
* `<tank id>` - id czołgu który ma wykonać skan
* `<count>` - ilość przeskanowanych pól
* `<x>`,`<y>` - współrzędne pola
* `<type>` - typ pola (EMPTY,WATER,WALL,SOLID,CRATE,BASE,SHOP)

**Przykład:**
```
> SCAN 12
< OK 4
  3 7 EMPTY
  4 7 WALL
  3 8 EMPTY
  4 8 CRATE
```
## MOVE
Przesuwa / obraca czołg
```
> MOVE <tank id> <L|R|F|B>
< OK
```
* `<tank id>` - id czołgu który ma zostać przemieszczony
* `<L|R|F|B>` - typ przemieszczenia
  * `L` - obrót w przeciwnie to ruchu wskazówek zegara
  * `R` - obrót zgodnie z ruchem wskazówek zegara
  * `F` - przemieszczenie o jedno pole w przód
  * `B` - przemieszczenie o jedno pole w tył

**Przykład:**
```
> MOVE 3 F
< OK
```
## SHOOT
Odpala pocisk
```
> SHOOT <tank id> <ammo type>
< OK
```
* `<tank id>` - id czołgu który ma wystrzelić
* `<ammo type>` - typ amunicji która ma zostać wystrzelona

**Przykład:**
```
> MOVE 3 1
< OK
```
## BUY
Kupuje amunicję, czołg musi znajdować się na polu `SHOP`
```
> BUY <tank id> <ammo type> <amount>
< OK
```
* `<tank id>` - id czołgu który ma dokonać zakupu
* `<ammo type>` - typ amunicji która ma zostać zakupiona
* `<amount>` - ilość amunicji która ma zostać zakupiona

**Przykład:**
```
> BUY 3 1 1000
< OK
```
## REPAIR
Naprawia czołg, czołg musi znajdować się na polu `SHOP`
```
> REPAIR <tank id>
< OK
```
* `<tank id>` - id czołgu który ma zostać naprawiony

**Przykład:**
```
> REPAIR 3
< OK
```
## SPAWN
Kupuje nowy czołg
```
> SPAWN <tank type>
< OK
```
* `<tank type>` - typ czołgu który m zostać zakupiony

**Przykład:**
```
> SPAWN 2
< OK
```


# Błędy
* **100** - internal server error
* **101** - unknown error
* **102** - unknown command
* **103** - wrong syntax
* **110** - authorization required
* **200** - you do not have such tank
* **201** - you can't drive through that field
* **202** - no moves left
* **203** - you do not have such ammunition
* **210** - there is no such tank type
* **211** - there is no such ammunition type
* **300** - not enough money
* **301** - you have to be in shop
* **302** - not enough space
