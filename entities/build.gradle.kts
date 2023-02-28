configurations {
    create("test")
}

tasks.register<Jar>("testArchive") {
    archiveBaseName.set("entities-test")
    from(project.the<SourceSetContainer>()["test"].output)
}

artifacts {
    add("test", tasks["testArchive"])
}
