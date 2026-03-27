/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetcaster.core.data.network

// Only feeds that return Access-Control-Allow-Origin headers are included here,
// as the browser blocks cross-origin requests without CORS support.
actual val SampleFeeds: List<String> = listOf(
    "https://fragmentedpodcast.com/feed/",
    "https://feeds.megaphone.fm/replyall",
    "https://feeds.npr.org/510289/podcast.xml",
    "https://feeds.99percentinvisible.org/99percentinvisible",
    "https://www.howstuffworks.com/podcasts/stuff-you-should-know.rss",
    "https://rss.art19.com/the-daily",
    "https://rss.art19.com/lisk",
    "https://omny.fm/shows/silence-is-not-an-option/playlists/podcast.rss",
    "https://feeds.simplecast.com/7PvD7RPL",
    "https://feeds.buzzsprout.com/1006078.rss",
)
