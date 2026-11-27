package com.example.kotlincommandbus

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.UUID

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private fun createUser(name: String, surname: String, age: Int): String {
        val body = mockMvc.perform(
            post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"name":"$name","surname":"$surname","age":$age}""")
        )
            .andExpect(status().isOk)
            .andReturn().response.contentAsString
        return objectMapper.readTree(body).get("id").asText()
    }

    @Test
    fun `saved user can be fetched by id`() {
        val id = createUser("Ada", "Lovelace", 36)

        mockMvc.perform(get("/api/user/$id"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.fullName").value("AdaLovelace"))
            .andExpect(jsonPath("$.age").value(36))
    }

    @Test
    fun `unknown id returns 404`() {
        mockMvc.perform(get("/api/user/${UUID.randomUUID()}"))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `malformed id returns 400`() {
        mockMvc.perform(get("/api/user/not-a-uuid"))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `list contains a created user`() {
        val id = createUser("Alan", "Turing", 41)

        mockMvc.perform(get("/api/user"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$[?(@.id == '$id')].fullName").value("AlanTuring"))
    }
}
