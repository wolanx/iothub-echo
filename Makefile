default:
	cat Makefile

a:
	./gradlew :jibDockerBuild

a:
	docker-compose up -d

b:
	docker-compose down
