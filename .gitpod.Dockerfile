FROM gitpod/workspace-full
                    
USER gitpod

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh \
             && sdk install java 14.0.2.j9-adpt \
             && sdk default java 14.0.2.j9-adpt"

RUN sudo apt-get update \
 && sudo DEBIAN_FRONTEND=noninteractive apt-get install -y \
   xvfb \
 && sudo rm -rf /var/lib/apt/lists/*