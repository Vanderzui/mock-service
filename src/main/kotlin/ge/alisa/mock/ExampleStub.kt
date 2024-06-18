package ge.alisa.mock

import com.github.tomakehurst.wiremock.client.WireMock.*
import org.springframework.stereotype.Component

@Component
class ExampleStub: StubInterface {

    override fun createStub() {
        val fileContent = this::class.java.classLoader.getResource("responses/example.json")?.readText()
        stubFor(
            get(urlEqualTo("/example"))
                .willReturn(
                    aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(fileContent)
                        .withStatus(200)
                )
        )
    }
}