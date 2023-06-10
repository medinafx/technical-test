## Ambiente
- Java version: 1.8+
- Maven version: 3.*
- Spring Boot version: 2.7.12.RELEASE

##  Descripción

API REST donde se detecta si un prisionero puede escapar tomando
en cuenta las siguientes consideraciones.

- La salida siempre está marcada como 'S'
- El prisionero es 'P'
- Las paredes están marcadas como '|'
- El prisionero solo puede caminar en las siguientes direcciones: arriba, abajo, derecha e izquierda
- El prisionero solo podrá andar por  ' '
- Pueden existir guardias, por lo cual el prisionero no podrá pasar por un camino si el guardia tiene visibilidad, la visibilidad puede ser bloqueada por una pared.
   - '>' guardia mirando a la derecha
   - '<' guardia mirando a la izquierda
   - '^' guardia mirando arriba
   - 'v' guardia mirando abajo

#### Representación JSON de una prisión

```json
{
  "prison": ["|||||||S||", "|P ||   |","||  | | |", "|v| | < |", "| |   | |", "|   |   |","|||||||||"]
}
```

## Ejemplos
Verifica si un prisionero puede escapar. Si puede escapar Retorna Status Code 200 OK, caso
contrario 403 Forbidden.
```
POST https://localhost:8080/prisoner

Body
{
  "prison": ["|||||||S||", "|P ||   |","||  | | |", "|v| | < |", "| |   | |", "|   |   |","|||||||||"]
}
```
Devuelve estadísticas de la prisión:
```
GET https://localhost:8080/stats

Response
{
 'count_successful_escape':40, 'count_unsuccessful_escape':100: 'ratio':0.4
}
```

## Comandos
- run:
```bash
./mvnw clean package -DskipTests; java -jar target/technical-test-0.0.1.jar
```

- test:
```bash
./mvnw clean test
```
## Cobertura

Para ver el informe de cobertura generado por JaCoCo code coverage ir a:

![Cobertura](https://github.com/medinafx/technical-test/blob/master/src/main/resources/img/img.png)

## API REST en Microsoft Azure

## URL Base

https://fmedina-challenge-backend.azurewebsites.net

```
POST https://fmedina-challenge-backend.azurewebsites.net/prisoner

Body
{
  "prison": ["|||||||S||", "|P ||   |","||  | | |", "|v| | < |", "| |   | |", "|   |   |","|||||||||"]
}
```
Devuelve estadísticas de la prisión:
```
GET https://fmedina-challenge-backend.azurewebsites.net/stats

Response
{
 'count_successful_escape':40, 'count_unsuccessful_escape':100: 'ratio':0.4
}