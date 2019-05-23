# Wheel-Of-Fortunes
Technologie sieciowe - projekt

W rozgrywce biorą udział 3-ki graczy. Ich zadaniem jest odgadniecie hasła. Na początku rozgrywki, hasło powinno być losowane z puli haseł dostępnych na serwerze.

Założenia:

-Użytkownik aplikacji może podać swój login. Login powinen być unikalny, więc serwer musi reagować, na powielenie się loginu u różnych klientów.

-Po określenieu unikalnego loginu, serwer roześle do podłączonych klientów informacje o pojawieniu się nowego uczestnika, natomiast ten nowo-podłączony otrzyma pełną listę loginów użytkowników, którzy byli już podłączeni.

-Aplikacja kliencka powinna posiadać interfejs graficzny.

-Musi zostać użyta klasa DatagramSocket
