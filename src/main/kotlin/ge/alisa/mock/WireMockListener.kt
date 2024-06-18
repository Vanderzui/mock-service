package ge.alisa.mock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import jakarta.annotation.PostConstruct
import org.springframework.boot.SpringApplication
import org.springframework.boot.SpringApplicationRunListener
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component

@Component
class WireMockListener(
    private val apiStubs: List<StubInterface>
) : SpringApplicationRunListener {

    private lateinit var server: WireMockServer

    @PostConstruct
    fun init() {
        server = WireMockServer(
            WireMockConfiguration.options()
                .port(8088)
        )
        server.start()
        WireMock.configureFor("localhost", 8088)
        createStubs()
    }

    private fun createStubs() {
        apiStubs.forEach { it.createStub() }
    }

}