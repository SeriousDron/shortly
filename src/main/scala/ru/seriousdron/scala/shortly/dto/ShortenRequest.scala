package ru.seriousdron.scala.shortly.dto

import java.net.URL

import colossus.protocols.http.HttpBodyDecoder

import scala.util.Try

/**
  * Created by seriousdron on 23.10.16.
  */
class ShortenRequest(val url: URL) {
  
}


object ShortenRequest {

  implicit object ShortenDecoder extends HttpBodyDecoder[ShortenRequest] {
    def decode(body: Array[Byte]) = Try {
      val data = new String(body)
      val parts = data.split("=")
      if (parts.length != 2) {
        throw new ArrayIndexOutOfBoundsException()
      }
      new ShortenRequest(new URL(parts(1)))
    }
  }

}

