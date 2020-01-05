package dev.pgordon

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates
import khttp.get
import org.json.JSONObject
import java.util.*


class TrainScheduleIntentHandler : RequestHandler {
    override fun canHandle(input: HandlerInput): Boolean {
        return input.matches(Predicates.intentName("TrainScheduleIntent"))
    }

    override fun handle(input: HandlerInput): Optional<Response> {
        val response = get("http://142.93.98.186:8080/api/departures?station=Iserbrook&filter=Airport")
            .jsonArray
        val speechText = when {
            response.length() == 0 -> "No trains. Probably an error in hvv-client"
            else -> {
                val timeUntilNextTrain = (response[0] as JSONObject)["timeInMinutes"]
                "$timeUntilNextTrain minutes"
            }
        }

        return input.responseBuilder
            .withSpeech(speechText)
            .withSimpleCard("Next train in", speechText)
            .build()
    }
}