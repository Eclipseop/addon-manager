fun main(args: Array<String>) {

  val addons = listOf(
    Addon("ElvUi", "https://github.com/tukui-org/ElvUI/archive/refs/heads/main.zip"),
    Addon("BigWigs", "https://github.com/BigWigsMods/BigWigs/releases/download/v267.1/BigWigs-v267.1.zip"),
    Addon("MoveAny", "https://github.com/d4kir92/MoveAny/releases/download/v1.0.61/MoveAny-v1.0.61.zip"),
    Addon("WeakAuras2", "https://github.com/WeakAuras/WeakAuras2/releases/download/5.3.7/WeakAuras-5.3.7.zip"),
    Addon("AstralKeys", "https://github.com/astralguild/AstralKeys/releases/download/3.74/AstralKeys-3.74.zip"),
    Addon("Details!", "https://github.com/Tercioo/Details-Damage-Meter/archive/refs/tags/Details.DF.Wrath.10.0.5.10661.148.zip"),
    Addon("Raider IO", "https://github.com/RaiderIO/raiderio-addon/releases/download/v202303040600/RaiderIO-v202303040600.zip")
  )

  for (addon in addons) {
    addon.writeToTemp()
    addon.unzipFromTemp()
  }
}