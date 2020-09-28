<img src="media/logo/ic_app.png" height="100px" />

# Crypto Tracker

[![Travis CI](https://travis-ci.org/fartem/crypto-tracker.svg?branch=master)](https://travis-ci.org/fartem/crypto-tracker)
[![Codebeat](https://codebeat.co/badges/5522fa61-f97e-4d99-b6f4-3f401108cf6b)](https://codebeat.co/projects/github-com-fartem-crypto-tracker-master)
[![Codecov](https://codecov.io/gh/fartem/crypto-tracker/branch/master/graph/badge.svg)](https://codecov.io/gh/fartem/crypto-tracker)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Crypto%20Tracker-brightgreen.svg?style=flat)](https://android-arsenal.com/details/3/7955)

## About

Demo application with statistics of some cryptocurrencies.

## API

### CoinMarketCap

__Resources__

[CoinMarketCap](https://pro.coinmarketcap.com).

__API key__

In `CurrencyService` class replace `API_KEY` in each header.

## Resources

Original icons provided by [cryptocurrency-icons](https://github.com/atomiclabs/cryptocurrency-icons).

## Download

<img src="media/qrcodes/github_download.png" height="150px" />

## Screenshots

<br/>
<p align="center">
  <img src="media/screenshots/screenshot_01.png" width="190" />
  <img src="media/screenshots/screenshot_02.png" width="190" />
  <img src="media/screenshots/screenshot_03.png" width="190" />
  <img src="media/screenshots/screenshot_04.png" width="190" />
</p>

## How to contribute

Read [Commit Convention](https://github.com/fartem/repository-rules/blob/master/commit-convention/COMMIT_CONVENTION.md). Make sure your build is green before you contribute your pull request. Then:

```shell
$ ./gradlew clean
$ ./gradlew build
$ ./gradlew connectedDebugImplDebugAndroidTest
```

If you don't see any error messages, submit your pull request.

## Contributors

* [@fartem](https://github.com/fartem) as Artem Fomchenkov
* [@alirezanazari](https://github.com/alirezanazari) as Alireza Nazari
