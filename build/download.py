#!/usr/bin/env python3

import requests
import os

basePath = os.path.normpath(os.path.realpath(__file__).replace("download.py", ""))

with open(basePath + "/forge-installer.jar", "w+b") as jar:
        forgeVer = "14.23.5.2860"
        mcVer = "1.12.2"
        url = (
            "https://maven.minecraftforge.net/net/minecraftforge/forge/"
            + mcVer
            + "-"
            + forgeVer
            + "/forge-"
            + mcVer
            + "-"
            + forgeVer
            + "-installer.jar"
        )
        r = requests.get(url)
        jar.write(r.content)
print("Forge installer Downloaded")