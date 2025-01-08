# contenttype-bug-reproducer

Reproduce the problem caused by quarkus-rest-client-jackson.

There is two branches :

- **main** : without quarkus-rest-client-jackson
- **issue** : with quarkus-rest-jackson

The only difference between these two branches is this dependency

## Steps to reproduce

```shell
git checkout main
mvn test
#OK
git checkout issue
mvn test
#KO
```