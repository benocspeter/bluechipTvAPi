# bluechip Tv Api
 
### Usage:
mvn clean package

java -jar target\BlueChipTvApi.jar

mvn jaxb2:schemagen

### Schema after generation
target\schemagen-work\compile_scope\schema1.xsd

### Test:
General search for TVs : http://localhost:8111/tvsearch?query={name}

Listing the details of a specific product: http://localhost:81111/details?termek={ID}
