FROM gitpod/workspace-full
                    
USER gitpod

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh \
             && sdk install java 14.0.2.j9-adpt \
             && sdk default java 14.0.2.j9-adpt"

RUN sudo apt-get update \
 && apt-get install libgtk2.0-0 libgtk-3-0 libgbm-dev libnotify-dev libgconf-2-4 libnss3 libxss1 libasound2 libxtst6 xauth xvfb