package dev.pgordon


import com.amazon.ask.Skill
import com.amazon.ask.SkillStreamHandler
import com.amazon.ask.Skills

val skill: Skill = Skills.standard()
    .addRequestHandlers(
        HelloWorldIntentHandler(),
        LaunchRequestHandler()
    ).build()

class HelloWorldStreamHandler : SkillStreamHandler(skill)