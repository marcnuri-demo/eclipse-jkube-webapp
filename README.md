# Eclipse JKube - WebApp Example

Very simple [Spring WebMVC](https://docs.spring.io/spring-framework/docs/5.2.7.RELEASE/spring-framework-reference/web.html#spring-web)
framework application to demonstrate Eclipse JKube's capabilities regarding Java Web Applications.

## How to Run

### Requirements

The project is based in Java 11.

In order to run this example you will need a valid K8s cluster accessible via `kubectl`.


### Compiling the project

```shell script
$ mvn clean package
```
This will create a deployable war in `./target/example-0.0.0-SNAPSHOT.war`

### Building the image (Docker/S2I)

#### Using docker

```shell script
$ mvn k8s:build
```

This will create a docker image tagged `webapp/example:latest`

#### Using S2I (requires OpenShift cluster)

```shell script
$ mvn oc:build
```

This will create an `ImageStream` with name `example` (`172.30.39.149:5000/jkube/example:latest`)

### Creating resource manifests

#### Kubernetes

```shell script
$ mvn k8s:resource
```

This will generate resource manifests in `target/classes/META-INF/jkube/kubernetes.yml`

#### OpenShift

```shell script
$ mvn oc:resource
```

This will generate resource manifests in `target/classes/META-INF/jkube/openshift.yml`

### Deploying into the cluster

#### Kubernetes

NOTE: If you built your image using Docker, you'll either need to have a shared Docker Daemon
with the cluster (e.g. `eval $(minikube docker-env)`) or push your image to an accessible
registry first (i.e. `mvn k8s:push`).

```shell script
$ mvn k8s:apply
```

A new `Deployment` will be created with name `example`

#### OpenShift

If you built your image using `oc:build`, your image will already be in the cluster, so 
there is no need to push it or have a shared Docker daemon.

```shell script
$ mvn oc:apply
```

### Testing the deployment

Your web application should now be deployed on the cluster, you can test the endpoint by:

```shell script
# Minikube
$ curl $(minikube ip):$(kubectl get svc example -n default -o jsonpath='{.spec.ports[].nodePort}')
Hello!!!
# OpenShift
$ curl $(kubectl get routes.route.openshift.io example -o jsonpath='{.spec.host}')
Hello!!!
```