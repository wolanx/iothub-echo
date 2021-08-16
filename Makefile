default:
	cat Makefile

b:
	./gradlew :jibDockerBuild

up:
	docker-compose up -d

d:
	docker-compose down
