default:
	cat Makefile

b:
	./gradlew :jibDockerBuild

up:
	docker-compose up -d

d:
	docker-compose down

bb:
	./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true -Dquarkus.native.native-image-xmx=8g
