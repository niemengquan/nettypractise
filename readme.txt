protobuf compile

protoc --java_out=src/main/java src/addressbook.proto


# use the follow command statement to generate grpc call stub

gradle generateProto

or

graldew generateProto
